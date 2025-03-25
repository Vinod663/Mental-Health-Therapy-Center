package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddReceptionistController {

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private TextField emailField;

    @FXML
    private TableColumn<?, ?> fullNameColumn;

    @FXML
    private TextField fullNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TableView<?> receptionistTable;

    @FXML
    private TableColumn<?, ?> usernameColumn;

    @FXML
    private TextField usernameField;

    @FXML
    void handleDeleteAction(ActionEvent event) {

    }

    @FXML
    void handleResetAction(ActionEvent event) {

    }

    @FXML
    void handleSaveAction(ActionEvent event) {

    }

    @FXML
    void handleUpdateAction(ActionEvent event) {

    }

}
