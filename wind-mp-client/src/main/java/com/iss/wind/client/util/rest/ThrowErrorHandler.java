package com.iss.wind.client.util.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

// 自定义restTemplate错误处理类 屏蔽restTemplate原来的错误处理
@Slf4j
public class ThrowErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) {
        // 返回false表示不管response的status是多少都返回没有错
        // 这里可以自己定义那些status code你认为是可以抛Error
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) {
    }
}
