package com.jiahe.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class BaseException extends RuntimeException {

    private final String code;

    private final String message;

    private final String error;

    public BaseException(BaseExceptionEnum baseExceptionEnum) {
        this.code = baseExceptionEnum.getCode();
        this.message = baseExceptionEnum.getMessage();
        this.error = null;
    }

    public BaseException(String code, String message) {
        this.code = code;
        this.message = message;
        this.error = null;
    }

    public BaseException(String message) {
        this.code = null;
        this.message = message;
        this.error = null;
    }
}
