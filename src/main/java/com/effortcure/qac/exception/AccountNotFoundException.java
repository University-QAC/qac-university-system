package com.effortcure.qac.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
        super("Account is not found");
    }
}
