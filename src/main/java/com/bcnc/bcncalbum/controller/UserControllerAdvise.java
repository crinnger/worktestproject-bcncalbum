package com.bcnc.bcncalbum.controller;

import com.bcnc.bcncalbum.exceptions.UserFetchException;
import com.bcnc.bcncalbum.model.ErrorResponse;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserControllerAdvise {

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ErrorResponse> handleWebClientResponseException(WebClientResponseException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(ErrorResponse.builder()
                        .date(LocalDateTime.now())
                        .error(ex.getMessage())
                        .status(ex.getStatusCode().value())
                        .build());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .status(500)
                .body(ErrorResponse.builder()
                        .date(LocalDateTime.now())
                        .error(ex.getMessage())
                        .status(500)
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity
                .status(500)
                .body(ErrorResponse.builder()
                        .date(LocalDateTime.now())
                        .error(ex.getMessage())
                        .status(500)
                        .build());
    }
}
