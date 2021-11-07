package com.example.demo.module.common;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(1001, "해당 유저는 존재하지 않습니다."),
    PASSWORD_NOT_MATCH(2001, "비밀번호가 틀렸습니다");
    private int errorCode;
    private String errorMessage;

    ErrorCode(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
