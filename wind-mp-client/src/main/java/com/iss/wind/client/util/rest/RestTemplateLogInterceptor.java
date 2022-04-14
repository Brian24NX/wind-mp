package com.iss.wind.client.util.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        //记录日志
        traceLog(execute);
        return execute;
    }

    // 打印一条访问后日志
    private void traceLog(ClientHttpResponse response) throws IOException {
        log.info("log-print",response.getStatusCode(), response.getStatusText());
        try {
            fun();
        }catch (Exception e){
            log.error("traceLog-errror");
        }

    }

    private ExecutorService executor = Executors.newCachedThreadPool();

    public void fun() throws Exception {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //要执行的业务代码，我们这里没有写方法，可以让线程休息几秒进行测试
                    Thread.sleep(10000);
                    log.info("execute success aysnc!");
                } catch (Exception e) {
                    throw new RuntimeException("ERRROOR！！");
                }
            }
        });
    }

}
