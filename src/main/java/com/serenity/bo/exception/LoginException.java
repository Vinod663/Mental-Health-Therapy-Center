package com.serenity.bo.exception;

public class LoginException extends ApplicationException {
    public LoginException(String message) {
        super("Login failed: " + message);
    }
}
