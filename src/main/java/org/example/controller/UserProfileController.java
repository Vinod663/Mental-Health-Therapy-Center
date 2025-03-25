package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.bo.BOFactory;
import org.example.bo.custom.BOTypes;
import org.example.bo.custom.LoginBO;
import org.example.dto.UserDto;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {

    LoginBO loginBO = (LoginBO) BOFactory.getInstance().getBO(BOTypes.LOGIN);

    @FXML
    private Button addReceptionistBtn;

    @FXML
    private Button contactAdminBtn;

    @FXML
    private Button editProfileBtn;

    @FXML
    private TextField emailField;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField roleField;

    @FXML
    private TextField usernameField;

    @FXML
    void addReceptionistAction(ActionEvent event) {

    }

    @FXML
    void adminBtnAction(ActionEvent event) {

    }

    @FXML
    void editProfileAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Objects.equals(loginBO.getRole(), "Admin")){
            contactAdminBtn.setVisible(false);
        }else{
            addReceptionistBtn.setDisable(true);
        }

        UserDto user = loginBO.getUser();
        if (user != null) {
            fullNameField.setText(user.getFullName());
            emailField.setText(user.getEmail());
            usernameField.setText(user.getUsername());
            roleField.setText(user.getRole());
        }


    }
}
