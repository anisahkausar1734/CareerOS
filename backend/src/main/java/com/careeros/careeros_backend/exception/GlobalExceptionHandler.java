package com.careeros.careeros_backend.exception;

import com.careeros.careeros_backend.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AuthResponse> handleException(Exception ex) {

        AuthResponse response = AuthResponse.builder()
                .success(false)
                .message("Something went wrong: " + ex.getMessage())
                .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}