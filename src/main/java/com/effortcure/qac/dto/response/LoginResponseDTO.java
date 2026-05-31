package com.effortcure.qac.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LoginResponseDTO {
    private String accessToken;
    private long accessTokenExpiresIn;
    private String expiresInUnit;
    private String tokenType;
    @JsonIgnore
    private String refreshToken;
    @JsonIgnore
    private Long refreshTokenExpiresIn;

    public LoginResponseDTO(String accessToken, long accessTokenExpiresIn, String expiresInUnit, String tokenType,
            String refreshToken, Long refreshTokenExpiresIn) {
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.expiresInUnit = expiresInUnit;
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getAccessTokenExpiresIn() {
        return accessTokenExpiresIn;
    }

    public void setAccessTokenExpiresIn(long accessTokenExpiresIn) {
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }

    public String getExpiresInUnit() {
        return expiresInUnit;
    }

    public void setExpiresInUnit(String expiresInUnit) {
        this.expiresInUnit = expiresInUnit;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }

    public void setRefreshTokenExpiresIn(Long refreshTokenExpiresIn) {
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

}