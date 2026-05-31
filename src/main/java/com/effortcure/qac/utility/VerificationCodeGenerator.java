package com.effortcure.qac.utility;

import java.security.SecureRandom;

public class VerificationCodeGenerator {
    public static String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
