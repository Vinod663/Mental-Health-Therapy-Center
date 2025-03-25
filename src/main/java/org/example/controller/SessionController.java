package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SessionController {

    @FXML
    private RadioButton cancelRadio;

    @FXML
    private RadioButton completetRadio;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button deleteBtn;

    @FXML
    private ComboBox<?> filterStatusCombo;

    @FXML
    private ComboBox<?> filterTherapistCombo;

    @FXML
    private TableColumn<?, ?> noteColumn;

    @FXML
    private ComboBox<?> patientCombo;

    @FXML
    private TableColumn<?, ?> patientNameColumn;

    @FXML
    private Label patientNameLabel;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField sessionNoteText;

    @FXML
    private Button sessionreportBtn;

    @FXML
    private RadioButton sheduleRadio;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableColumn<?, ?> therapistColumn;

    @FXML
    private ComboBox<?> therapistCombo;

    @FXML
    private Label therapistNameLabel;

    @FXML
    private TableView<?> therapySessionTable;

    @FXML
    private TableColumn<?, ?> timeColumn;

    @FXML
    private TextField timeText;

    @FXML
    private Button updateBtn;

}
