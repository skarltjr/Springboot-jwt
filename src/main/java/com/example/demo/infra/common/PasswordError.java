package com.example.demo.infra.common;

import lombok.Setter;

@Setter
public class PasswordError extends RuntimeException {
    private String message;

    public PasswordError(String message) {
        super(message);
        this.message = message;
    }
}
