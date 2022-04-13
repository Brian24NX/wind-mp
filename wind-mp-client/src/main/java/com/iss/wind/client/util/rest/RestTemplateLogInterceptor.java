package com.iss.wind.client.util.rest;

import com.iss.wind.client.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

// 自定义restTemplate拦截器  在拦截器中进行了请求信息的打印，还对请求的返回做了异常处理(造成本次问题的问题的根源)
@Component
@Slf4j
public class RestTemplateLogInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

        // 打印访问前日志
        log.info("request-start,url={},method={},RequestBody={}",httpRequest.getURI(), httpRequest.getMethod(), new String(bytes, StandardCharsets.UTF_8));
        //访问执行请求
        ClientHttpResponse execute = clientHttpRequestExecution.execute(httpRequest, bytes);
        // 打印访问后日志
        log.error("request-end： url={}, statusCode={}, statusText={}", httpRequest.getURI(), execute.getStatusCode(), execute.getStatusText());
        // 请求的统一异常处理
//        if (!execute.getStatusCode().is2xxSuccessful()) {
//             throw new BusinessException("请求异常或超时");
//        }

        //记录日志
        traceLog(execute);
        return execute;
    }

    // 打印一条访问后日志
    private void traceLog(ClientHttpResponse response) throws IOException {
        log.info("log-print",response.getStatusCode(), response.getStatusText());
    }
}
