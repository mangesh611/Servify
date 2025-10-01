package com.example.servicer.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}


