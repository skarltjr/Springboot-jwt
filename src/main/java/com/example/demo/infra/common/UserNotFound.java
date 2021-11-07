package com.example.demo.infra.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFound extends RuntimeException {
    private String message;

    public UserNotFound(String message) {
        super(message);
        this.message = message;
    }
}
