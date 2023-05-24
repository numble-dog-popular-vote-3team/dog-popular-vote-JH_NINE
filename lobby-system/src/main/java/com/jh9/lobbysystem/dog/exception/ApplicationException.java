package com.jh9.lobbysystem.dog.exception;

public class ApplicationException extends RuntimeException{

    private String errorCode;
    private String message;

    protected ApplicationException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
