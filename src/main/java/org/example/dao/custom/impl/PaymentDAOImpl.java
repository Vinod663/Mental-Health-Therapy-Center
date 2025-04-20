package org.example.dao.custom.impl;

import org.example.dao.custom.PaymentDAO;
import org.example.entity.Patients;
import org.example.entity.Payment;
import org.example.entity.Registration;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public List<Patients> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean save(Patients patients) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Patients patients) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteByPK(String pk) throws SQLException {
        return false;
    }

    @Override
    public boolean save(Payment payment, Session session) throws SQLException {
        session.persist(payment);
        return true;
    }

    @Override
    public Payment get(int id, Session session) throws SQLException {
        try {
            return session.createQuery(
                            "FROM Payment p WHERE p.patient.id = :patientId", Payment.class)
                    .setParameter("patientId", id)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to get Payment for patient ID: " + id, e);
        }
    }

    @Override
    public boolean update(Payment payment, Session session) throws SQLException {
        session.merge(payment);
        return true;
    }

}
