package com.effortcure.qac.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.effortcure.qac.payload.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandller {
    @ExceptionHandler(ExpiredTokenException.class)
    ResponseEntity<ApiResponse<Void>> handleExpiredToken(ExpiredTokenException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(400, ex.getMessage(), null));
    }

    @ExceptionHandler(InvalidTokenException.class)
    ResponseEntity<ApiResponse<Void>> handleInvalidToken(InvalidTokenException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(401, ex.getMessage(), null));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    ResponseEntity<ApiResponse<Void>> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(409, ex.getMessage(), null));
    }

    @ExceptionHandler(EmailSendingException.class)
    ResponseEntity<ApiResponse<Void>> handleEmailSendingException(EmailSendingException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(500, ex.getMessage() + " | Caused by: " + ex.getCause().getMessage(), null));
    }

    @ExceptionHandler(EmailNotExistException.class)
    ResponseEntity<ApiResponse<Void>> handleEmailNotExist(EmailNotExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404, ex.getMessage(), null));
    }

    @ExceptionHandler(VerificationCodeExpiredException.class)
    ResponseEntity<ApiResponse<Void>> handleVerificationCodeExpired(VerificationCodeExpiredException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(400, ex.getMessage(), null));
    }

    @ExceptionHandler(WrongVerificationCodeException.class)
    ResponseEntity<ApiResponse<Void>> handleWrongVerificationCode(WrongVerificationCodeException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(401, ex.getMessage(), null));
    }

    @ExceptionHandler(WrongPasswordException.class)
    ResponseEntity<ApiResponse<Void>> WrongPassword(WrongPasswordException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(401, ex.getMessage(), null));
    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    ResponseEntity<ApiResponse<Void>> handleRefreshTokenNotFound(RefreshTokenNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404, ex.getMessage(), null));
    }

    @ExceptionHandler(RevokedRefreshTokenException.class)
    ResponseEntity<ApiResponse<Void>> handleRevokedRefreshToken(RevokedRefreshTokenException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(400, ex.getMessage(), null));
    }

    @ExceptionHandler(AccountNotVerifiedException.class)
    ResponseEntity<ApiResponse<Void>> handleAccountNotVerified(AccountNotVerifiedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>(403, ex.getMessage(), null));
    }

    @ExceptionHandler(AccountNotFoundException.class)
    ResponseEntity<ApiResponse<Void>> handleAccountNotFound(AccountNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404, ex.getMessage(), null));
    }
}
