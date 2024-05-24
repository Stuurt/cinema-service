package com.cinema.service.exception.handler;

import com.cinema.service.exception.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestResponseControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        ErrorResponse error =
                new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value()
                        , ex.getLocalizedMessage(),
                        null
                );
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}