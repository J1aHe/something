package com.jiahe.exception;

import com.jiahe.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<BaseResponse> globalExceptionHandler(BaseException baseException) {

        BaseResponse response = new BaseResponse();
        response.setMessage(baseException.getMessage());
        response.setCode(
                baseException.getCode() == null ? String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        : baseException.getCode());

        if ("401".equals(baseException.getCode())) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        if ("403".equals(baseException.getCode())) {
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
