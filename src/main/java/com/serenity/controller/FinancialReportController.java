package com.serenity.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FinancialReportController {

    @FXML
    private Button btnExportExcel;

    @FXML
    private Button btnExportPDF;

    @FXML
    private Button btnGenerateReport;

    @FXML
    private ComboBox<?> cmbDataRange;

    @FXML
    private ComboBox<?> cmbPatient;

    @FXML
    private ComboBox<?> cmbPaymentType;

    @FXML
    private ComboBox<?> cmbProgram;

    @FXML
    private ComboBox<?> cmbSortBy;

    @FXML
    private TableColumn<?, ?> colAmountPaid;

    @FXML
    private TableColumn<?, ?> colPatientId;

    @FXML
    private TableColumn<?, ?> colPatientName;

    @FXML
    private TableColumn<?, ?> colPaymentType;

    @FXML
    private TableColumn<?, ?> colPendingAmount;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private Label lblTotalAmountPaid;

    @FXML
    private Label lblTotalBalance;

    @FXML
    private Label lblTotalRecords;

    @FXML
    private TableView<?> tblFinancialReport;

    @FXML
    private TextField txtCustomLimit;

    @FXML
    void btnExportExcelOnAction(ActionEvent event) {

    }

    @FXML
    void btnExportPDFOnAction(ActionEvent event) {

    }

    @FXML
    void btnGenerateReportOnAction(ActionEvent event) {
    //checking
    //checking
    //checking
    //checking
    }

    @FXML
    void cmbDataRangeAction(ActionEvent event) {

    }

    @FXML
    void cmbPatientAction(ActionEvent event) {

    }

    @FXML
    void cmbPaymentTypeAction(ActionEvent event) {

    }

    @FXML
    void cmbProgramAction(ActionEvent event) {

    }

    @FXML
    void cmbSortByAction(ActionEvent event) {

    }

    @FXML
    void txtCustomLimitAction(ActionEvent event) {

    }

}
