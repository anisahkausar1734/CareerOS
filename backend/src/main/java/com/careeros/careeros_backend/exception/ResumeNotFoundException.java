package com.careeros.careeros_backend.exception;

public class ResumeNotFoundException
        extends RuntimeException {

    public ResumeNotFoundException(String message) {
        super(message);
    }
}