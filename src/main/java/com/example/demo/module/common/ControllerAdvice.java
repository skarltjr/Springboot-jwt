package com.example.demo.module.common;


import com.example.demo.module.common.exception.PasswordNotMatchException;
import com.example.demo.module.common.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND,
                ErrorCode.USER_NOT_FOUND.getErrorCode(), ErrorCode.USER_NOT_FOUND.getErrorMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordNotMath(PasswordNotMatchException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,
                ErrorCode.PASSWORD_NOT_MATCH.getErrorCode(), ErrorCode.PASSWORD_NOT_MATCH.getErrorMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
