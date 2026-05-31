package com.effortcure.qac.dto.request;

public class VerificationCodeRequestDTO {
    String email;
    String code;

    public VerificationCodeRequestDTO(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
