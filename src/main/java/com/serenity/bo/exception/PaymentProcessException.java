package com.serenity.bo.exception;

public class PaymentProcessException extends ApplicationException {
    public PaymentProcessException(String message) {
        super("Payment processing failed: " + message);
    }
}
