package org.example.bo.custom.impl;

import org.example.bo.custom.PaymentBO;
import org.example.dao.DAOFactory;
import org.example.dao.DAOTypes;
import org.example.dao.custom.PaymentDAO;
import org.example.dao.custom.TherapistDAO;
import org.example.dto.PaymentDto;
import org.example.entity.Payment;

import java.math.BigDecimal;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOTypes.PAYMENT);
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
            return null; // or throw an exception if you prefer
        }
    }

    @Override
    public BigDecimal findPaymentAmount(int patientId, String date, String time) {
        return paymentDAO.getPaymentAmountByDetails(patientId, date, time);
    }
}
