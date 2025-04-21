package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Payment;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Payment,String> {
    boolean save(Payment payment, Session session) throws SQLException;

    Payment get(int id, Session session) throws SQLException;

    boolean update(Payment payment, Session session) throws SQLException;

    Payment getPAyDetail(int patientId);
    BigDecimal getPaymentAmountByDetails(int patientId, String date, String time);
}
