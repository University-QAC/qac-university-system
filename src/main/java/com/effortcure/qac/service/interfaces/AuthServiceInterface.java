package com.effortcure.qac.service.interfaces;

import com.effortcure.qac.dto.request.LoginRequestDTO;
import com.effortcure.qac.dto.request.RegisterRequestDTO;
import com.effortcure.qac.dto.request.VerificationCodeRequestDTO;
import com.effortcure.qac.dto.response.LoginResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthServiceInterface {

    public void checkEmailExistance(String email);

    public void createNewAccount(RegisterRequestDTO registerRequestDTO);

    public void removeUnverifiedAccount(String email);

    public LoginResponseDTO validateEmailVerificationCode(VerificationCodeRequestDTO verificationCodeRequestDTO,
            HttpServletRequest request);

    public LoginResponseDTO authenticateAccount(LoginRequestDTO loginRequestDTO, HttpServletRequest request);

    public LoginResponseDTO refreshAccessToken(HttpServletRequest request);

    public void logOut(HttpServletRequest request);

    public void sendVerificationCode(String email);
}
