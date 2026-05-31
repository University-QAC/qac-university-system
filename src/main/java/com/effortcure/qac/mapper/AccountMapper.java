package com.effortcure.qac.mapper;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.effortcure.qac.dto.request.RegisterRequestDTO;
import com.effortcure.qac.model.Account;
import com.effortcure.qac.utility.VerificationCodeGenerator;

@Component
public class AccountMapper {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Account toEntity(RegisterRequestDTO registerRequestDTO) {
        Account account = new Account();
        account.setEmail(registerRequestDTO.getEmail());
        account.setUsername(registerRequestDTO.getName());
        account.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        account.setVerificationCode(VerificationCodeGenerator.generateVerificationCode());
        account.setCodeExpiredAt(LocalDateTime.now(ZoneOffset.UTC).plusMinutes(10));
        return account;
    }
}
