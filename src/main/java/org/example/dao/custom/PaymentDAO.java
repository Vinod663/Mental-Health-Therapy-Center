package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Patients;
import org.example.entity.Payment;
import org.hibernate.Session;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Patients,String> {
    boolean save(Payment payment, Session session) throws SQLException;

    Payment get(int id, Session session) throws SQLException;

    boolean update(Payment payment, Session session) throws SQLException;
}
