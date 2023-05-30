package com.jh9.lobbysystem.dog.exception.wrapper;

import java.io.Serializable;
import org.springframework.http.HttpStatus;

public class ErrorResponse implements Serializable {

    private HttpStatus httpStatus;
    private String code;
    private String message;
    private String detail;

    private ErrorResponse() {}

    private ErrorResponse(ErrorCode code) {
        this.httpStatus = code.getHttpStatus();
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    private ErrorResponse(ErrorCode code, String detail) {
        this.httpStatus = code.getHttpStatus();
        this.code = code.getCode();
        this.message = code.getMessage();
        this.detail = detail;
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(ErrorCode code, String message) {
        return new ErrorResponse(code, message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}
