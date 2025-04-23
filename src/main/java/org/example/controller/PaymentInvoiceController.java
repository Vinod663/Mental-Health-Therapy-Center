package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.dto.PaymentDto;
import org.example.view.tdm.PaymentTm;

import java.awt.print.PrinterJob;
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentInvoiceController implements Initializable {

    @FXML
    public TextField paymentTypeField;

    @FXML
    public AnchorPane PrintingAnchor;

    @FXML
    private TextField amountPaidField;

    @FXML
    private Button closeButton;

    @FXML
    private TextField duePaymentField;

    @FXML
    private TextField patientIdField;

    @FXML
    private TextField patientNameField;

    @FXML
    private TextField paymentIdField;

    @FXML
    private Button printButton;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField therapistIdField;

    @FXML
    private TextField therapistNameField;

    private PaymentDto paymentDetails;

    @FXML
    void closeBtnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void printBtnAction(ActionEvent event) {
        PrinterJob job = PrinterJob.getPrinterJob();
        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
                statusLabel.setText("Printing...");//temp
            } catch (Exception e) {
                statusLabel.setText("Print failed: " + e.getMessage());
            }
        } else {
            statusLabel.setText("Print canceled.");
    }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPaymentDto(PaymentDto paymentDto) {
        paymentDetails = paymentDto;
        setInvoiceDetails(); // call after setting the data
    }

    private void setInvoiceDetails() {
        if (paymentDetails != null) {
            paymentIdField.setText(String.format("PAY%03d", paymentDetails.getPaymentId()));
            patientIdField.setText(String.format("P%03d", paymentDetails.getPatientId()));
            patientNameField.setText(paymentDetails.getPatientName());
            therapistIdField.setText(String.valueOf(paymentDetails.getProgramId()));
            therapistNameField.setText(paymentDetails.getProgramName());
            amountPaidField.setText(String.valueOf(paymentDetails.getAmount()));
            duePaymentField.setText(String.valueOf(paymentDetails.getBalancePayment()));
            paymentTypeField.setText(paymentDetails.getPaymentType());
        }
    }
}
