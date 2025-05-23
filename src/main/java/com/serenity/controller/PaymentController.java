package com.serenity.controller;

import com.serenity.dto.PaymentDto;
import com.serenity.dto.TherapyProgramDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.serenity.bo.BOFactory;
import com.serenity.bo.custom.BOTypes;
import com.serenity.bo.custom.PatientBO;
import com.serenity.bo.custom.PaymentBO;
import com.serenity.bo.custom.TherapyProgramBO;
import com.serenity.bo.exception.PaymentProcessException;
import com.serenity.view.tdm.PaymentTm;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PaymentController implements Initializable {

    TherapyProgramBO programBO   = (TherapyProgramBO) BOFactory.getInstance().getBO(BOTypes.PROGRAM);
    PatientBO patientBO   = (PatientBO) BOFactory.getInstance().getBO(BOTypes.PATIENT);
    PaymentBO paymentBO   = (PaymentBO) BOFactory.getInstance().getBO(BOTypes.PAYMENT);
    private ObservableList<PaymentTm> paymentList = FXCollections.observableArrayList();

    @FXML
    private Button clearBtn;

    @FXML
    public Button invoiceBtn;

    @FXML
    private ComboBox<String> cmbPatient;

    @FXML
    private ComboBox<String> cmbPaymentType;

    @FXML
    private ComboBox<String> cmbProgram;

    @FXML
    private TableColumn<PaymentTm, BigDecimal> colAmount;

    @FXML
    private TableColumn<PaymentTm, String> colDate;

    @FXML
    private TableColumn<PaymentTm, String> colPatientName;

    @FXML
    private TableColumn<PaymentTm, Integer> colPaymentId;

    @FXML
    private TableColumn<PaymentTm, String> colPaymentType;

    @FXML
    private TableColumn<PaymentTm, String> colProgramName;

    @FXML
    private Button deleteBtn;

    @FXML
    private Label lblTotalPaid;

    @FXML
    private Button saveBtn;

    @FXML
    private TableView<PaymentTm> tblPayments;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtRemainingBalance;

    @FXML
    private Button updateBtn;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        setPaymentId();
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
        invoiceBtn.setDisable(true);
        saveBtn.setDisable(false);
        lblTotalPaid.setText("0.00");
    }

    private void clearFields() {
        txtAmount.clear();
        txtRemainingBalance.clear();
        lblTotalPaid.setText("0.00");
        cmbPatient.getSelectionModel().clearSelection();
        cmbProgram.getItems().clear();
        cmbPaymentType.getSelectionModel().clearSelection();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        PaymentTm paymentTm = tblPayments.getSelectionModel().getSelectedItem();

        if (paymentTm != null) {
            // Show confirmation alert before deleting
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Deletion");
            confirmationAlert.setHeaderText("Are you sure you want to delete this payment?");
            confirmationAlert.setContentText("Payment ID: " + paymentTm.getPaymentId());

            Optional<ButtonType> result = confirmationAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    boolean isDeleted = paymentBO.deletePayment(paymentTm.getPaymentId());
                    if (isDeleted) {
                        loadAllPatients();
                        clearFields();
                        new Alert(Alert.AlertType.INFORMATION, "Payment deleted successfully!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete payment!").show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Deletion canceled.").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a payment to delete!").show();
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (validateInputs()) {
            PaymentDto paymentDto = collectPaymentData();
            try {
                boolean isSaved = paymentBO.savePayment(paymentDto);
                if (isSaved) {
                    clearFields();
                    setPaymentId();
                    loadAllPatients();
                    new Alert(Alert.AlertType.INFORMATION, "Payment saved successfully!").show();
                } else {
                    throw new PaymentProcessException("Failed to save payment!");
                    /*new Alert(Alert.AlertType.ERROR, "Failed to save payment!").show();*/
                }
            }
            /*catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }*/
            catch (PaymentProcessException e){
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (validateInputs()) {
            PaymentTm selectedItem = tblPayments.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a payment to update!").show();
                return;
            }
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setPaymentId(selectedItem.getPaymentId());
            paymentDto.setPatientId(Integer.parseInt(cmbPatient.getValue().split(" - ")[0]));
            paymentDto.setProgramId(String.valueOf(cmbProgram.getValue().split(" - ")[0]));
            paymentDto.setPaymentType(cmbPaymentType.getValue());
            paymentDto.setDate(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
            paymentDto.setTime(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
            paymentDto.setAmount(new BigDecimal(txtAmount.getText().trim()));
            paymentDto.setBalancePayment(new BigDecimal(txtRemainingBalance.getText().trim()).add(selectedItem.getAmount()).subtract(new BigDecimal(txtAmount.getText().trim())));

            try {
                boolean isUpdated = paymentBO.updatePayment(paymentDto);
                if (isUpdated) {
                    clearFields();
                    setPaymentId();
                    loadAllPatients();
                    new Alert(Alert.AlertType.INFORMATION, "Payment updated successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update payment!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void tblPaymentsOnMouseClicked(MouseEvent event) {
        updateBtn.setDisable(false);
        deleteBtn.setDisable(false);
        invoiceBtn.setDisable(false);
        saveBtn.setDisable(true);
        PaymentTm paymentTm = tblPayments.getSelectionModel().getSelectedItem();
        if (paymentTm != null) {

            int Id = paymentTm.getPaymentId();
            txtPaymentId.setText(String.format("PAY%03d", Id));

            cmbPatient.setValue(String.valueOf(paymentBO.getPaymentById(Id).getPatientId()) + " - " + paymentBO.getPaymentById(Id).getPatientName());
            cmbProgram.setValue(String.valueOf(paymentBO.getPaymentById(Id).getProgramId()) + " - " + paymentBO.getPaymentById(Id).getProgramName());
            txtAmount.setText(String.valueOf(paymentTm.getAmount()));
            cmbPaymentType.setValue(paymentTm.getPaymentType());

            PaymentDto payment = paymentBO.getPaymentByPatientId(Integer.parseInt(String.valueOf(paymentBO.getPaymentById(Id).getPatientId())));

            BigDecimal balancePayment=payment.getBalancePayment();
            txtRemainingBalance.setText(String.valueOf(balancePayment));

            BigDecimal TotalPaid = null;
            try {
                TotalPaid = programBO.getProgram(paymentBO.getPaymentById(Id).getProgramId()).getProgramCost().subtract(balancePayment);
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to load Total Payed Amount: " + e.getMessage()).show();
                throw new RuntimeException(e);
            }
            if (TotalPaid != null) {
                lblTotalPaid.setText(String.valueOf(TotalPaid));
            } else {
                lblTotalPaid.setText("0.00");
            }

        }

    }

    @FXML
    void txtAmountOnAction(ActionEvent event) {

    }

    @FXML
    public void cmbPatientAction(ActionEvent actionEvent) {
        String patientSelected = cmbPatient.getValue();

        if (patientSelected == null || patientSelected.isEmpty()) {
            // Prevent the error
            return;
        }

        String patientId = patientSelected.split(" - ")[0];

        try {
            cmbProgram.getItems().clear();
            List<TherapyProgramDto> programList = programBO.getAllProgramOptions(patientId);

            for (TherapyProgramDto dto : programList) {
                cmbProgram.getItems().add(dto.getProgramId() + " - " + dto.getProgramName());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load Programs: " + e.getMessage()).show();
            e.printStackTrace();
        }

        // Get the latest payment details for the selected patient
        PaymentDto payment = paymentBO.getPaymentByPatientId(Integer.parseInt(patientId));

        try {
            if (payment != null) {
                txtAmount.clear();
                lblTotalPaid.setText("0.00");
                txtRemainingBalance.setText(String.valueOf(payment.getBalancePayment()));
            } else {
                // Clear fields if no payment found
                txtRemainingBalance.clear();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load Balance Payment: " + e.getMessage()).show();
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void programAction(ActionEvent actionEvent) {
        String programSelected = cmbProgram.getValue();

        if (programSelected == null || programSelected.isEmpty()) {
            // Prevent the error
            return;
        }

        try {
            String programId = programSelected.split(" - ")[0];
            BigDecimal TotalPaid = programBO.getProgram(programId).getProgramCost().subtract(new BigDecimal(txtRemainingBalance.getText()));
            lblTotalPaid.setText(String.valueOf(TotalPaid));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load Total Payed Amount: " + e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void payTypeCombo(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
        invoiceBtn.setDisable(true);

        setupComboBoxes();
        setPaymentId();
        setupTableColumns();
        loadAllPatients();
    }

    private void setupComboBoxes() {
        // Setup payment type combobox
        cmbPaymentType.getItems().addAll("Cash", "Card", "Online Transfer");

        try {
            List<String> patients = patientBO.getAllPatients().stream()
                    .map(patient -> patient.getId() + " - " + patient.getName())
                    .collect(Collectors.toList());
            cmbPatient.getItems().addAll(patients);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load patients: " + e.getMessage()).show();
            e.printStackTrace();
        }

        // Setup program combobox
        /*try {
            List<TherapyProgramDto> programs = programBO.getAll();
            for (TherapyProgramDto program : programs) {
                cmbProgram.getItems().add(program.getProgramId() + " - " + program.getProgramName());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load programs: " + e.getMessage()).show();
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }

    private void setPaymentId() {
        try {
            String nextId = paymentBO.getNextPaymentId();
            txtPaymentId.setText(nextId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate patient ID: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }
    public void setupTableColumns(){
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colPaymentType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblPayments.setItems(paymentList);
    }

    private void loadAllPatients() {
        try {
            List<PaymentTm> payments = paymentBO.getAllPayments();
            paymentList.clear();
            paymentList.addAll(payments);
            tblPayments.setItems(paymentList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load Payments: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private boolean validateInputs() {
        StringBuilder errors = new StringBuilder();

        if (cmbPatient.getValue() == null) {
            errors.append("Patient must be selected\n");
        }

        if (cmbProgram.getValue() == null) {
            errors.append("Program must be selected\n");
        }

        if (cmbPaymentType.getValue() == null) {
            errors.append("payment Type must be selected\n");
        }

        if (txtPaymentId.getText().isEmpty()) {
            errors.append("Payment ID must be provided\n");
        }

        if(txtAmount.getText().isEmpty()){
            errors.append("Amount must be provided\n");
        }

        if (!txtAmount.getText().isEmpty()) {
            try {
                double amount = Double.parseDouble(txtAmount.getText().trim());
                if (amount <= 0 || amount > Double.parseDouble(txtRemainingBalance.getText().trim())) {
                    errors.append("Amount must be a positive number and Less than Pending Payment\n");
                }
            } catch (NumberFormatException e) {
                errors.append("Payment amount must be a valid number\n");
            }
        }

        if (errors.length() > 0) {
            new Alert(Alert.AlertType.ERROR, errors.toString()).show();
            return false;
        }

        return true;
    }

    private PaymentDto collectPaymentData() {
        PaymentDto paymentDto = new PaymentDto();

        String idText = txtPaymentId.getText().trim();
        if (idText.startsWith("PAY")) {
            idText = idText.substring(3);
        }
        paymentDto.setPaymentId(Integer.parseInt(idText));
        paymentDto.setPatientId(Integer.parseInt(cmbPatient.getValue().split(" - ")[0]));
        paymentDto.setProgramId(String.valueOf(cmbProgram.getValue().split(" - ")[0]));
        paymentDto.setPaymentType(cmbPaymentType.getValue());
        paymentDto.setDate(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        paymentDto.setTime(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        paymentDto.setAmount(new BigDecimal(txtAmount.getText().trim()));
        paymentDto.setBalancePayment(new BigDecimal(txtRemainingBalance.getText().trim()).subtract(new BigDecimal(txtAmount.getText().trim())));


        return paymentDto;
    }


    public void invoiceBtnAction(ActionEvent actionEvent) {
        PaymentTm paymentTm = tblPayments.getSelectionModel().getSelectedItem();
        if (paymentTm != null) {
            PaymentDto paymentDto = null;
            try {
                paymentDto = new PaymentDto();
                paymentDto.setPaymentId(paymentTm.getPaymentId());
                paymentDto.setPatientId(Integer.parseInt(cmbPatient.getValue().split(" - ")[0]));
                paymentDto.setPatientName(paymentTm.getPatientName());
                paymentDto.setProgramId(String.valueOf(cmbProgram.getValue().split(" - ")[0]));
                paymentDto.setProgramName(paymentTm.getProgramName());
                paymentDto.setPaymentType(paymentTm.getPaymentType());
                paymentDto.setAmount(paymentTm.getAmount());
                paymentDto.setBalancePayment(new BigDecimal(txtRemainingBalance.getText().trim()));
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Payment-Invoice.fxml"));
                Parent load= fxmlLoader.load();

                PaymentInvoiceController controller = fxmlLoader.getController();
                controller.setPaymentDto(paymentDto);

                Stage stage = new Stage();
                stage.setTitle("Payment Invoice");
                stage.setScene(new Scene(load));
                stage.setResizable(false);
                stage.setFullScreen(false);

                stage.initModality(Modality.APPLICATION_MODAL);

                Window owner = invoiceBtn.getScene().getWindow();
                stage.initOwner(owner);

                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load invoice: " + e.getMessage()).show();
            }
        }
    }
}
