package com.myapp.detector.service.exception;

public class NameCounterException extends RuntimeException {
    public NameCounterException(String message, Throwable cause) {
        super(message, cause);
    }
}
