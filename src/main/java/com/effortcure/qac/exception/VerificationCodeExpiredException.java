package com.effortcure.qac.exception;

public class VerificationCodeExpiredException extends RuntimeException {
    public VerificationCodeExpiredException() {
        super("Verification code you sent is expired");
    }
}
