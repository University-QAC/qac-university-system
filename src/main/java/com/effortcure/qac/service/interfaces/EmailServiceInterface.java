package com.effortcure.qac.service.interfaces;

public interface EmailServiceInterface {
    public void sendVerificationCodeEmail(String email, String verificationCode);
}
