package org.example.bo.exception;

public class SessionConflictException extends ApplicationException {
    public SessionConflictException(String message) {
        super("Session conflict: " + message);
    }
}
