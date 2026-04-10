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
}
