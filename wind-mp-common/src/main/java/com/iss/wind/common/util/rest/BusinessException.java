package com.iss.wind.common.util.rest;

import lombok.Data;

/** 这里继承RuntimeException异常 **/
@Data
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 2332608236621015980L;

    private String type = "B-";
    private Object[] msgArgs;
    /** 用于存放后端返回的数据 **/
    private Object data;

    public BusinessException(String message) {
        super(message);
    }

}