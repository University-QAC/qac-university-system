package com.effortcure.qac.exception;

public class AccountNotVerifiedException extends RuntimeException {
    public AccountNotVerifiedException(String email) {
        super("'" + email + "' is not verified, we have sent an email with a verification code");
    }
}
