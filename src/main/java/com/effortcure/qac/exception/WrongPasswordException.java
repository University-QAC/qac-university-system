package com.effortcure.qac.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("Password is incorrect");
    }
}
