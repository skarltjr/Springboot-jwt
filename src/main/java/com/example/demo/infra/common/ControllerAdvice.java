package com.example.demo.infra.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity handleNotfound(UsernameNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, "not found", e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(PasswordError.class)
    public ResponseEntity handleIllegalPassword(PasswordError e) {
        ErrorResponse illegal_password = new ErrorResponse(HttpStatus.BAD_REQUEST, "Illegal password", e.getMessage());
        return ResponseEntity.badRequest().body(illegal_password);
    }
}
