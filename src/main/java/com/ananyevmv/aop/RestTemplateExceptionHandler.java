package com.ananyevmv.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class RestTemplateExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleGeneralException(Throwable throwable) {
        Map<String, String> errorMap = Map.of("Error", throwable.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
