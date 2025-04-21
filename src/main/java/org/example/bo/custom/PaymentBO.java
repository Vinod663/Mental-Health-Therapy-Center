package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.PaymentDto;

import java.math.BigDecimal;

public interface PaymentBO extends SuperBO {
    PaymentDto getPaymentByPatientId(int patientId);
    BigDecimal findPaymentAmount(int patientId, String date, String time);
}
