package org.example;

import at.favre.lib.crypto.bcrypt.BCrypt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.config.FactoryConfiguration;
import org.example.entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Main extends Application {
    private static FactoryConfiguration factory;
    public static void main(String[] args) {
        /*factory = FactoryConfiguration.getInstance();
        Session session = factory.getSession();

        Users user = new Users();

        user.setRole("receptionist");
        user.setUsername("vinod");


        user.setPassword(BCrypt.withDefaults().hashToString(12, "685845".toCharArray()));

        try {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Failed to save user");
            e.printStackTrace();
            throw new RuntimeException(e);

        }



        session.close();*/


        launch(args);



    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Serenity Therapy Center");
        stage.setScene(scene);
        stage.show();
    }
}