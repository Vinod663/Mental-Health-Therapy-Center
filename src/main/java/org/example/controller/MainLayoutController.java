package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.BOTypes;
import org.example.bo.custom.LoginBO;

import java.net.URL;
import java.util.ResourceBundle;

public class MainLayoutController implements Initializable {
    LoginBO loginBO = (LoginBO) BOFactory.getInstance().getBO(BOTypes.LOGIN);


    @FXML
    private Button AccountBtn;

    @FXML
    private AnchorPane ContentAnchor;

    @FXML
    private Button LogoutBtn;

    @FXML
    private Button appoinmentsBtn;

    @FXML
    private Button patientsBtn;

    @FXML
    private Button paymentBtn;

    @FXML
    private Button programsBtn;

    @FXML
    private Button reportBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button therapistBtn;

    @FXML
    void AccountBtnAction(ActionEvent event) {

    }

    @FXML
    void LogoutBtnAction(ActionEvent event) {

    }

    @FXML
    void appoinmentsBtnAction(ActionEvent event) {

    }

    @FXML
    void patientsBtnAction(ActionEvent event) {

    }

    @FXML
    void paymentBtnAction(ActionEvent event) {

    }

    @FXML
    void programsBtnAction(ActionEvent event) {

    }

    @FXML
    void reoprtBtnAction(ActionEvent event) {

    }

    @FXML
    void settingsBtnAction(ActionEvent event) {

    }

    @FXML
    void therapistBtnAction(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String role = loginBO.getRole();
        AccountBtn.setText(role);
        if (role.equals("admin")) {
            patientsBtn.setDisable(true);
            appoinmentsBtn.setDisable(true);
            paymentBtn.setDisable(true);

        } else {
            therapistBtn.setDisable(true);
            programsBtn.setDisable(true);
        }

    }
}
