package com.jh9.lobbysystem.dog.exception.wrapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = Shape.OBJECT)
public enum ErrorCode {

    // Dog
    BAD_SEARCH_CONDITION(HttpStatus.BAD_REQUEST, "D001", "잘못된 검색조건입니다."),
    ALREADY_VOTE(HttpStatus.BAD_REQUEST, "D003", "이미 투표를 실시했습니다."),

    // javax.validation
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "V001", "잘못된 입력값입니다."),

    // COMMON
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C001", "서버 내부 에러"),
    RESOURCE_NOT_FOUND(HttpStatus.BAD_REQUEST, "C002", "찾고자 하는 정보가 없습니다."),
    EXPIRED_CODE(HttpStatus.BAD_REQUEST, "C003", "Expired Code");

    private HttpStatus httpStatus;
    private String code;
    private String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
    }

    public static ErrorCode of(String code) {
        switch (code) {
            case "MIN":
            case "NotBlank":
            case "NotNull":
                return ErrorCode.INVALID_INPUT;

            default:
                throw new IllegalArgumentException("지원하지 않는 코드입니다.");
        }
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
}
