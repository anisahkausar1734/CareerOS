package com.careeros.careeros_backend.exception;

import com.careeros.careeros_backend.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.careeros.careeros_backend.exception.ResumeAnalysisException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<AuthResponse> handleUserNotFoundException(
            UserNotFoundException ex
    ) {

        AuthResponse response = AuthResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ResumeNotFoundException.class)
    public ResponseEntity<AuthResponse> handleResumeNotFoundException(
            ResumeNotFoundException ex
    ) {

        AuthResponse response = AuthResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }
@ExceptionHandler(ResumeAnalysisException.class)
public ResponseEntity<AuthResponse>
handleResumeAnalysisException(
        ResumeAnalysisException ex
) {

    AuthResponse response =
            AuthResponse.builder()
                    .success(false)
                    .message(ex.getMessage())
                    .build();

    return new ResponseEntity<>(
            response,
            HttpStatus.BAD_REQUEST
    );
}
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AuthResponse> handleException(
            Exception ex
    ) {

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