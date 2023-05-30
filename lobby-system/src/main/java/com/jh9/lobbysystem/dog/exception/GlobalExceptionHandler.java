package com.jh9.lobbysystem.dog.exception;

import com.jh9.lobbysystem.dog.exception.wrapper.ErrorCode;
import com.jh9.lobbysystem.dog.exception.wrapper.ErrorResponse;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String LOG_FORMAT = "Class: {}, Code: {}, Message: {}";

    @ExceptionHandler(ApplicationException.class)
    public Mono<ServerResponse> applicationException(ApplicationException e) {
        ErrorCode errorCode = e.getErrorCode();
        log.warn(LOG_FORMAT, e.getClass().getSimpleName(), errorCode, e.getMessage());
        return ServerResponse
            .status(errorCode.getHttpStatus())
            .bodyValue(ErrorResponse.of(errorCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Mono<ServerResponse> methodValidException(
        MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = makeErrorResponse(e.getBindingResult());
        log.warn(LOG_FORMAT, e.getClass().getSimpleName(), errorResponse.getCode(),
            errorResponse.getDetail());

        return ServerResponse.status(HttpStatus.BAD_REQUEST)
            .bodyValue(errorResponse);
    }

    private ErrorResponse makeErrorResponse(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = Objects.requireNonNull(bindingResult.getFieldError());

            String detail = fieldError.getDefaultMessage();
            String bindResultCode = fieldError.getCode();

            ErrorCode errorCode = ErrorCode.of(bindResultCode);

            return ErrorResponse.of(errorCode, detail);
        }

        return ErrorResponse.of(ErrorCode.INVALID_INPUT);
    }

    @ExceptionHandler(DataAccessException.class)
    public Mono<ServerResponse> dataAccessException(DataAccessException e) {
        log.error(LOG_FORMAT,
            e.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return ServerResponse
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .bodyValue(ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(RuntimeException.class)
    public Mono<ServerResponse> runtimeException(RuntimeException e) {

        log.error(LOG_FORMAT,
            e.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return ServerResponse
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .bodyValue(ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
