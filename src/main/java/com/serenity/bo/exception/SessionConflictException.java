package com.serenity.bo.exception;

public class SessionConflictException extends ApplicationException {
    public SessionConflictException(String message) {
        super("Session conflict: " + message);
    }
}
