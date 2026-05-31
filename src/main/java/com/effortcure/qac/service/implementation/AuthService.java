package com.effortcure.qac.service.implementation;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.effortcure.qac.dto.request.LoginRequestDTO;
import com.effortcure.qac.dto.request.RegisterRequestDTO;
import com.effortcure.qac.dto.request.VerificationCodeRequestDTO;
import com.effortcure.qac.dto.response.LoginResponseDTO;
import com.effortcure.qac.exception.AccountNotFoundException;
import com.effortcure.qac.exception.AccountNotVerifiedException;
import com.effortcure.qac.exception.EmailAlreadyExistsException;
import com.effortcure.qac.exception.EmailNotExistException;
import com.effortcure.qac.exception.RefreshTokenNotFoundException;
import com.effortcure.qac.exception.RevokedRefreshTokenException;
import com.effortcure.qac.exception.VerificationCodeExpiredException;
import com.effortcure.qac.exception.WrongPasswordException;
import com.effortcure.qac.exception.WrongVerificationCodeException;
import com.effortcure.qac.mapper.AccountMapper;
import com.effortcure.qac.mapper.RefreshTokenMapper;
import com.effortcure.qac.model.Account;
import com.effortcure.qac.model.RefreshToken;
import com.effortcure.qac.repository.AccountRepository;
import com.effortcure.qac.repository.RefreshTokenRepository;
import com.effortcure.qac.security.JwtService;
import com.effortcure.qac.service.interfaces.AuthServiceInterface;
import com.effortcure.qac.service.interfaces.ClientInfoExtractorServiceInterface;
import com.effortcure.qac.service.interfaces.EmailServiceInterface;
import com.effortcure.qac.utility.VerificationCodeGenerator;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class AuthService implements AuthServiceInterface {

    private AccountRepository accountRepository;
    private AccountMapper accountMapper;
    private EmailServiceInterface emailServiceInterface;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private RefreshTokenRepository refreshTokenRepository;
    private RefreshTokenMapper refreshTokenMapper;
    private ClientInfoExtractorServiceInterface clientInfoExtractorServiceInterface;

    @Autowired
    public AuthService(AccountRepository accountRepository, AccountMapper accountMapper,
            EmailServiceInterface emailServiceInterface, PasswordEncoder passwordEncoder, JwtService jwtService,
            RefreshTokenRepository refreshTokenRepository, RefreshTokenMapper refreshTokenMapper,
            ClientInfoExtractorServiceInterface clientInfoExtractorServiceInterface) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.emailServiceInterface = emailServiceInterface;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenMapper = refreshTokenMapper;
        this.clientInfoExtractorServiceInterface = clientInfoExtractorServiceInterface;
    }

    @Override
    public void checkEmailExistance(String email) {
        if (accountRepository.existsByEmail(email))
            throw new EmailAlreadyExistsException(email);
    }

    @Override
    @Transactional
    public void createNewAccount(RegisterRequestDTO registerRequestDTO) {
        if (accountRepository.existsByEmail(registerRequestDTO.getEmail()))
            throw new EmailAlreadyExistsException(registerRequestDTO.getEmail());

        Account savedAccount = accountRepository.save(accountMapper.toEntity(registerRequestDTO));

        emailServiceInterface.sendVerificationCodeEmail(savedAccount.getEmail(), savedAccount.getVerificationCode());
    }

    @Override
    public void removeUnverifiedAccount(String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotExistException(email));
        accountRepository.delete(account);
    }

    @Override
    @Transactional
    public LoginResponseDTO validateEmailVerificationCode(VerificationCodeRequestDTO verificationCodeRequestDTO,
            HttpServletRequest request) {
        Account account = accountRepository.findByEmail(verificationCodeRequestDTO.getEmail())
                .orElseThrow(() -> new EmailNotExistException(verificationCodeRequestDTO.getEmail()));

        if (!account.getVerificationCode().equals(verificationCodeRequestDTO.getCode()))
            throw new WrongVerificationCodeException();

        if (account.getCodeExpiredAt().isBefore(LocalDateTime.now(ZoneOffset.UTC)))
            throw new VerificationCodeExpiredException();

        account.setIsVerified(true);
        accountRepository.save(account);

        refreshTokenRepository.revokeAllRefreshTokens(account.getUuid());

        String accessToken = jwtService.generateAccessToken(account);
        String refreshToken = jwtService.generateRefreshToken(account);
        String clientIpAddress = clientInfoExtractorServiceInterface.extractClientIp(request);
        String clientDeviceInfo = clientInfoExtractorServiceInterface.extractDeviceInfo(request);

        RefreshToken savedRefreshToken = refreshTokenRepository
                .save(refreshTokenMapper.toEntity(refreshToken, account, clientIpAddress, clientDeviceInfo));

        return new LoginResponseDTO(accessToken, jwtService.getAccessTokenExpiration(), "ms", "Bearer",
                savedRefreshToken.getToken(), jwtService.getRefreshTokenExpiration());

    }

    @Override
    @Transactional(dontRollbackOn = AccountNotVerifiedException.class)
    public LoginResponseDTO authenticateAccount(LoginRequestDTO loginRequestDTO, HttpServletRequest request) {
        Account account = accountRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new EmailNotExistException(loginRequestDTO.getEmail()));

        if (!Boolean.TRUE.equals(account.getIsVerified())) {
            account.setVerificationCode(VerificationCodeGenerator.generateVerificationCode());
            account.setCodeExpiredAt(LocalDateTime.now(ZoneOffset.UTC).plusMinutes(10));
            accountRepository.save(account);
            emailServiceInterface.sendVerificationCodeEmail(account.getEmail(), account.getVerificationCode());
            throw new AccountNotVerifiedException(account.getEmail());
        }

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), account.getPassword()))
            throw new WrongPasswordException();

        String accessToken = jwtService.generateAccessToken(account);
        String refreshToken = jwtService.generateRefreshToken(account);
        String clientIpAddress = clientInfoExtractorServiceInterface.extractClientIp(request);
        String clientDeviceInfo = clientInfoExtractorServiceInterface.extractDeviceInfo(request);

        RefreshToken savedRefreshToken = refreshTokenRepository
                .save(refreshTokenMapper.toEntity(refreshToken, account, clientIpAddress, clientDeviceInfo));

        return new LoginResponseDTO(accessToken, jwtService.getAccessTokenExpiration(), "ms", "Bearer",
                savedRefreshToken.getToken(), jwtService.getRefreshTokenExpiration());
    }

    @Override
    @Transactional
    public LoginResponseDTO refreshAccessToken(HttpServletRequest request) {
        String extractedRefreshToken = extractRefreshToken(request);
        if (extractedRefreshToken == null)
            throw new RefreshTokenNotFoundException();

        jwtService.isTokenExpired(extractedRefreshToken);
        Boolean isRevoked = refreshTokenRepository.isRevoked(extractedRefreshToken);
        if (Boolean.TRUE.equals(isRevoked))
            throw new RevokedRefreshTokenException();

        Account account = accountRepository.findById(UUID.fromString(jwtService.extractSubject(extractedRefreshToken)))
                .orElseThrow(() -> new AccountNotFoundException());

        String accessToken = jwtService.generateAccessToken(account);
        String refreshToken = jwtService.generateRefreshToken(account);
        String clientIpAddress = clientInfoExtractorServiceInterface.extractClientIp(request);
        String clientDeviceInfo = clientInfoExtractorServiceInterface.extractDeviceInfo(request);

        RefreshToken savedRefreshToken = refreshTokenRepository
                .save(refreshTokenMapper.toEntity(refreshToken, account, clientIpAddress, clientDeviceInfo));

        refreshTokenRepository.revokeRefreshToken(extractedRefreshToken);

        return new LoginResponseDTO(accessToken, jwtService.getAccessTokenExpiration(), "ms", "Bearer",
                savedRefreshToken.getToken(), jwtService.getRefreshTokenExpiration());
    }

    @Override
    public void logOut(HttpServletRequest request) {
        String extractedRefreshToken = extractRefreshToken(request);
        if (extractedRefreshToken != null) {
            refreshTokenRepository.revokeRefreshToken(extractedRefreshToken);
        }
    }

    @Override
    @Transactional
    public void sendVerificationCode(String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotExistException(email));

        account.setIsVerified(false);
        account.setVerificationCode(VerificationCodeGenerator.generateVerificationCode());
        account.setCodeExpiredAt(LocalDateTime.now(ZoneOffset.UTC).plusMinutes(10));
        accountRepository.save(account);

        emailServiceInterface.sendVerificationCodeEmail(email, account.getVerificationCode());
    }

    private String extractRefreshToken(HttpServletRequest request) {
        if (request.getCookies() == null)
            return null;
        for (Cookie cookie : request.getCookies()) {
            if ("refresh-token".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
