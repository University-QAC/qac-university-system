package com.effortcure.qac.exception;

public class WrongVerificationCodeException extends RuntimeException {
    public WrongVerificationCodeException() {
        super("Verification code is incorrect");
    }
}
