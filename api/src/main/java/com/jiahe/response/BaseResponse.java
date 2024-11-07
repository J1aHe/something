package com.jiahe.response;

import lombok.Data;

@Data
public class BaseResponse {

    private String code;

    private String message;

    private Object data;

    public static BaseResponse success() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode("success");
        return baseResponse;
    }

    public static BaseResponse failed() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode("fail");
        return baseResponse;
    }

    public BaseResponse message(String message) {
        this.message = message;
        return this;
    }

    public BaseResponse data(Object data) {
        this.data = data;
        return this;
    }

}
