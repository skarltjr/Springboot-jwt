package com.example.demo.module.common.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final int errorCode;

    public UserNotFoundException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
