package com.example.demo.infra.common;

import lombok.Getter;


@Getter
public class PasswordError extends RuntimeException {
    private String message;

    public PasswordError(String message) {
        super(message);
        this.message = message;
    }
}
