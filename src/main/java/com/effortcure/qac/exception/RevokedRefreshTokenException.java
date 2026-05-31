package com.effortcure.qac.exception;

public class RevokedRefreshTokenException extends RuntimeException {
    public RevokedRefreshTokenException() {
        super("Refresh token is revoked");
    }
}
