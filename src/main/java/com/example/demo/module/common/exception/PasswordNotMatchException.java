package com.example.demo.module.common.exception;

import lombok.Getter;

@Getter
public class PasswordNotMatchException extends RuntimeException{
    private final int errorCode;

    public PasswordNotMatchException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
