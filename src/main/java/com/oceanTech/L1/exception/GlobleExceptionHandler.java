package com.oceanTech.L1.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobleExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        response.put("data", null);
        response.put("errorCode", ErrorCode.EMPLOYEE_INVALID_CODE_FORMAT.getCode());
        response.put("errorMessage", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Map<String, Object>> handleAppExceptions(AppException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", ex.getData());
        response.put("errorCode", ex.getErrorCode().getCode());
        response.put("errorMessage", ex.getErrorCode().getMessage());

        return new ResponseEntity<>(response, ex.getErrorCode().getStatusCode());
    }
}
