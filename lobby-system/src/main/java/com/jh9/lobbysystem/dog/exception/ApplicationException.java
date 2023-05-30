package com.jh9.lobbysystem.dog.exception;

import com.jh9.lobbysystem.dog.exception.wrapper.ErrorCode;

public class ApplicationException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;

    protected ApplicationException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
