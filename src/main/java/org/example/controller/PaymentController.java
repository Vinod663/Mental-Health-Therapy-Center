package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PaymentController {

    @FXML
    private Button clearBtn;

    @FXML
    private ComboBox<?> cmbPatient;

    @FXML
    private ComboBox<?> cmbPaymentType;

    @FXML
    private ComboBox<?> cmbProgram;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPatientName;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colPaymentType;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private Button deleteBtn;

    @FXML
    private Label lblTotalPaid;

    @FXML
    private Button saveBtn;

    @FXML
    private TableView<?> tblPayments;

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

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void tblPaymentsOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void txtAmountOnAction(ActionEvent event) {

    }

}
