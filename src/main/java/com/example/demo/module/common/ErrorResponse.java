package com.example.demo.module.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private int errorCode;
    private String message;

    public ErrorResponse(HttpStatus httpStatus,int errorCode,String message) {
        this.timestamp = LocalDateTime.now();
        this.status = httpStatus.value();
        this.errorCode = errorCode;
        this.message = message;
    }
}
