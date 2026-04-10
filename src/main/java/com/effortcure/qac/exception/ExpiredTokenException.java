package com.effortcure.qac.exception;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException() {
        super("Token is expired");
    }
}
