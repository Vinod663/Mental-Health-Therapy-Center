package org.example.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.bo.BOFactory;
import org.example.bo.custom.BOTypes;
import org.example.bo.custom.LoginBO;
import org.example.config.FactoryConfiguration;
import org.example.entity.Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class LoginController {

    LoginBO loginBO = (LoginBO) BOFactory.getInstance().getBO(BOTypes.LOGIN);
    private String role;

    @FXML
    private Button LoginBtn;

    @FXML
    private PasswordField PswField;

    @FXML
    private TextField UsernameTextId;

    @FXML
    private AnchorPane ancLogin;

    @FXML
    void LoginAction(ActionEvent event) throws IOException {
        /*try {
            Session session = FactoryConfiguration.getInstance().getSession();

            Query<Users> query = session.createQuery("from Users u where u.username=:username", Users.class);
            query.setParameter("username", UsernameTextId.getText());
            *//*query.setParameter("password", BCrypt.withDefaults().hashToString(12, PswField.getText().toCharArray()));*//*
            List<Users> list = query.list();

            if (!list.isEmpty()) {
                System.out.println(list.get(0).getRole());
                *//*System.out.println(list.get(0).getPassword());*//*
                if(!list.isEmpty() && BCrypt.verifyer().verify(PswField.getText().toCharArray(), list.get(0).getPassword()).verified) {
                    System.out.println("Login successful!");
                    return;
                } else {
                    System.out.println("Login failed password incorrect");
                    return;
                }

            }
            System.out.println("Login failed Username and Password Incorrect");

            session.close();
        } catch (HibernateException e) {
            System.out.println("Login failed");
            *//*e.printStackTrace();*//*
        }*/
        boolean authenticate = loginBO.authenticate(UsernameTextId.getText(), PswField.getText());
        if (authenticate) {
           /* System.out.println("Login successful!");*/
            role = loginBO.getRole();
            System.out.println("role-"+role);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainLayout.fxml"));
            AnchorPane newPane=fxmlLoader.load();

            AnchorPane.setTopAnchor(newPane, 0.0);
            AnchorPane.setRightAnchor(newPane, 0.0);
            AnchorPane.setBottomAnchor(newPane, 0.0);
            AnchorPane.setLeftAnchor(newPane, 0.0);

            newPane.setTranslateX(ancLogin.getWidth());
            ancLogin.getChildren().setAll(newPane);

            Platform.runLater(() -> {
                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(newPane.translateXProperty(), 0, Interpolator.EASE_IN);
                KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
                timeline.getKeyFrames().add(keyFrame);
                timeline.setOnFinished(event1 -> {
                    ancLogin.getChildren().clear();
                    ancLogin.getChildren().add(newPane);
                });
                timeline.play();
            });
        } else {
            System.out.println("Try again");
        }
    }

}
