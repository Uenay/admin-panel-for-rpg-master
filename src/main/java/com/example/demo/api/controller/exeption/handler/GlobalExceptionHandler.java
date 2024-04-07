package com.example.demo.api.controller.exeption.handler;

import com.example.demo.api.response.BadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<BadResponse> handleValidationException(ConstraintViolationException exception) {
        BadResponse badResponse = BadResponse.builder()
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(badResponse, HttpStatus.BAD_REQUEST);
    }
}
