package com.jiahe.exception.user;

import com.jiahe.exception.BaseException;
import com.jiahe.exception.BaseExceptionEnum;

public class UserException extends BaseException {


    public UserException(String code, String message, String error) {
        super(code, message, error);
    }

    public UserException(BaseExceptionEnum baseExceptionEnum) {
        super(baseExceptionEnum);
    }

    public UserException(String code, String message) {
        super(code, message);
    }

    public UserException(String message) {
        super(message);
    }
}
