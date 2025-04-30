package com.serenity.bo.custom;

import com.serenity.bo.SuperBO;
import com.serenity.dto.PaymentDto;
import com.serenity.view.tdm.PaymentTm;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {
    PaymentDto getPaymentByPatientId(int patientId);
    BigDecimal findPaymentAmount(int patientId, String date, String time);
    String getNextPaymentId() throws SQLException;

    List<PaymentTm> getAllPayments() throws SQLException;

    PaymentDto getPaymentById(int paymentId);

    boolean savePayment(PaymentDto paymentDto);

    boolean deletePayment(int paymentId);

    boolean updatePayment(PaymentDto paymentDto);
}
