package com.iss.wind.common.enums.error;

import com.hanson.rest.enmus.ErrorCode;
import lombok.Getter;

/**
 * @author Hanson
 * @date 2022/1/11  16:12
 */
@Getter
public enum ErrorEnum implements ErrorCode {
    REQUEST_TIMEOUT("408", "请求超时"),
    ;

    private String code;
    private String message;

    private ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
