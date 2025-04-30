package com.serenity.bo.custom.impl;

import com.serenity.bo.custom.PaymentBO;
import com.serenity.config.FactoryConfiguration;
import com.serenity.dao.DAOFactory;
import com.serenity.dao.DAOTypes;
import com.serenity.dao.custom.PatientDAO;
import com.serenity.dao.custom.PaymentDAO;
import com.serenity.dao.custom.TherapyProgramDAO;
import com.serenity.dto.PaymentDto;
import com.serenity.entity.Payment;
import com.serenity.view.tdm.PaymentTm;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    private final FactoryConfiguration factoryConfiguration=FactoryConfiguration.getInstance();
    PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOTypes.PAYMENT);
    PatientDAO patientDAO = DAOFactory.getInstance().getDAO(DAOTypes.PATIENT);
    TherapyProgramDAO programDAO = DAOFactory.getInstance().getDAO(DAOTypes.PROGRAM);

    @Override
    public PaymentDto getPaymentByPatientId(int patientId) {
        Payment payment=paymentDAO.getPAyDetail(patientId);
        if (payment != null) {
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setPaymentId(payment.getPaymentId());
            paymentDto.setAmount(payment.getAmount());
            paymentDto.setBalancePayment(payment.getBalancePayment());
            return paymentDto;
        } else {
            return null;
        }
    }

    @Override
    public BigDecimal findPaymentAmount(int patientId, String date, String time) {
        return paymentDAO.getPaymentAmountByDetails(patientId, date, time);
    }

    @Override
    public String getNextPaymentId() throws SQLException {
        Session session = factoryConfiguration.getInstance().getSession();
        try {
            int lastId = paymentDAO.getLastId(session);
            return String.format("PAY%03d", (lastId + 1));
        } finally {
            session.close();
        }
    }

    @Override
    public List<PaymentTm> getAllPayments() throws SQLException {
        Session session = factoryConfiguration.getInstance().getSession();
        try {
            List<Payment> all = paymentDAO.getAll();
            List<PaymentTm> paymentTms = new ArrayList<>();

            for (Payment payment : all) {
                PaymentTm paymentTm = new PaymentTm();
                paymentTm.setPaymentId(payment.getPaymentId());
                paymentTm.setPatientName(payment.getPatient().getName());
                paymentTm.setAmount(payment.getAmount());
                paymentTm.setProgramName(payment.getProgram().getProgramName());
                paymentTm.setDate(payment.getDate());
                paymentTm.setPaymentType(payment.getPaymentType());

                paymentTms.add(paymentTm);
            }

            return paymentTms;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public PaymentDto getPaymentById(int paymentId) {
        Payment payment = null;
        try {
            payment = paymentDAO.getFromPayId(paymentId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if (payment != null) {
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setPaymentId(payment.getPaymentId());
            paymentDto.setAmount(payment.getAmount());
            paymentDto.setBalancePayment(payment.getBalancePayment());
            paymentDto.setDate(payment.getDate());
            paymentDto.setTime(payment.getTime());
            paymentDto.setPatientId(payment.getPatient().getId());
            paymentDto.setProgramId(payment.getProgram().getProgramId());
            paymentDto.setPatientName(payment.getPatient().getName());
            paymentDto.setProgramName(payment.getProgram().getProgramName());
            paymentDto.setPaymentType(payment.getPaymentType());
            return paymentDto;
        } else {
            return null;
        }
    }

    @Override
    public boolean savePayment(PaymentDto paymentDto) {
        try {
            Payment payment = new Payment();
            payment.setPaymentId(paymentDto.getPaymentId());
            payment.setAmount(paymentDto.getAmount());
            payment.setBalancePayment(paymentDto.getBalancePayment());
            payment.setDate(paymentDto.getDate());
            payment.setTime(paymentDto.getTime());
            payment.setPaymentType(paymentDto.getPaymentType());

            payment.setPatient(patientDAO.getPatientById(paymentDto.getPatientId()));
            payment.setProgram(programDAO.getProgram(paymentDto.getProgramId()));

            return paymentDAO.save(payment);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save payment: " + e.getMessage());
        }
    }

    @Override
    public boolean deletePayment(int paymentId) {
        try {
            return paymentDAO.delete(paymentId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updatePayment(PaymentDto paymentDto) {
        try {
            Payment payment = new Payment();
            Payment fromPayId = paymentDAO.getFromPayId(paymentDto.getPaymentId());

            payment.setPaymentId(paymentDto.getPaymentId());
            payment.setAmount(paymentDto.getAmount());
            payment.setBalancePayment(paymentDto.getBalancePayment());
            payment.setDate(fromPayId.getDate());
            payment.setTime(fromPayId.getTime());
            payment.setPaymentType(paymentDto.getPaymentType());

            payment.setPatient(fromPayId.getPatient());
            payment.setProgram(fromPayId.getProgram());

            return paymentDAO.update(payment);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update payment: " + e.getMessage());
        }
    }

}

