package org.example.bo.exception;

public class RegisterException extends ApplicationException {
    public RegisterException(String message) {
        super("Registration failed: " + message);
    }
}
