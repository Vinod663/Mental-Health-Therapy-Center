module Mental.Health.Therapy.Center {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires net.sf.jasperreports.core;
    requires java.mail;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires bcrypt;
    requires java.desktop;

    opens com.serenity.entity to org.hibernate.orm.core;
    opens com.serenity.view.tdm to javafx.base;
    opens com.serenity.config to jakarta.persistence;

    opens com.serenity.controller to javafx.fxml;


    exports com.serenity;
    exports com.serenity.view.tdm;
}