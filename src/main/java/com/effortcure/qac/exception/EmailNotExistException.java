package com.effortcure.qac.exception;

public class EmailNotExistException extends RuntimeException {
    public EmailNotExistException(String email) {
        super("An account with email '" + email + "' doesn't exist");
    }
}