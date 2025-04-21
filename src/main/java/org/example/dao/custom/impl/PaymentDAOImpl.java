package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.PaymentDAO;

import org.example.entity.Payment;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    private final FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();

    @Override
    public List<Payment> getAll() throws Exception {
        return List.of();
    }

    @Override
    public boolean save(Payment payment) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Payment payment) throws SQLException {
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
                            "FROM Payment p WHERE p.patient.id = :patientId ORDER BY p.paymentId DESC", Payment.class)
                    .setParameter("patientId", id)
                    .setMaxResults(1)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to get latest Payment for patient ID: " + id, e);
        }
    }


    @Override
    public boolean update(Payment payment, Session session) throws SQLException {
        session.merge(payment);
        return true;
    }

    @Override
    public Payment getPAyDetail(int patientId) {
        Session session2 = factoryConfiguration.getSession();
        try {
            return session2.createQuery(
                            "FROM Payment p WHERE p.patient.id = :patientId ORDER BY p.paymentId DESC", Payment.class)
                    .setParameter("patientId", patientId)
                    .setMaxResults(1)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get latest Payment for patient ID: " + patientId, e);
        } finally {
            session2.close();
        }
    }

    @Override
    public BigDecimal getPaymentAmountByDetails(int patientId, String date, String time) {
        Session session2 = factoryConfiguration.getSession();
        Transaction transaction = null;
        BigDecimal amount = BigDecimal.ZERO;

        try {
            transaction = session2.beginTransaction();

            String hql = "SELECT p.amount FROM Payment p " +
                    "WHERE p.patient.id = :patientId " +
                    "AND p.date = :date " +
                    "AND p.time = :time";

            Query<BigDecimal> query = session2.createQuery(hql, BigDecimal.class);
            query.setParameter("patientId", patientId);
            query.setParameter("date", date);
            query.setParameter("time", time);

            amount = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session2.close();
        }

        return amount != null ? amount : BigDecimal.ZERO;
    }


}
