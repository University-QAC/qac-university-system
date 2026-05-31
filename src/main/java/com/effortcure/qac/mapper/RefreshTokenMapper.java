package com.effortcure.qac.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.effortcure.qac.model.Account;
import com.effortcure.qac.model.RefreshToken;
import com.effortcure.qac.security.JwtService;

@Component
public class RefreshTokenMapper {

    private JwtService jwtService;

    @Autowired
    public RefreshTokenMapper(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public RefreshToken toEntity(String token, Account account, String ipAddress, String deviceInfo) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(token);
        refreshToken.setExpiresAt(jwtService.getTokenExpirationTime(token));
        refreshToken.setIpAddress(ipAddress);
        refreshToken.setDeviceInfo(deviceInfo);
        refreshToken.setAccount(account);
        return refreshToken;
    }
}
