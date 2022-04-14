package com.iss.wind.client.util.rest;

import com.google.common.io.ByteStreams;
import com.iss.wind.dao.domain.ThirdInvokeRecordPo;
import com.iss.wind.dao.mappers.ThirdInvokeRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ExecutorService executor = Executors.newCachedThreadPool();

    @Autowired
    private ThirdInvokeRecordMapper thirdInvokeRecordMapper;

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        long startTime = System.currentTimeMillis();
        // 打印访问前日志
        log.info("request-start,url={},method={},RequestBody={}",httpRequest.getURI(), httpRequest.getMethod(), new String(bytes, StandardCharsets.UTF_8));
        //访问执行请求
        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, bytes);
        // 打印访问后日志
        log.error("request-end： url={}, statusCode={}, statusText={}", httpRequest.getURI(), response.getStatusCode(), response.getStatusText());
        //记录日志
        ThirdInvokeRecordPo tir =  new ThirdInvokeRecordPo();
        tir.setUrl(httpRequest.getURI().toString());
        tir.setReq(new String(bytes,StandardCharsets.UTF_8));
        tir.setResp(response.getStatusText());
        tir.setConsumeTime(System.currentTimeMillis() - startTime+"ms");
        tir.setHttpStatus(response.getStatusCode()+"");
        traceLog(tir);
        return response;
    }

    // 打印一条访问后日志
    private void traceLog(ThirdInvokeRecordPo tir) throws IOException {
        try {
            traceThirdInvokeRecordLog(tir);
        }catch (Exception e){
            log.error("traceLog-errror");
        }

    }

    public void traceThirdInvokeRecordLog(ThirdInvokeRecordPo tir) throws Exception {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //执行记录日志
                    thirdInvokeRecordMapper.add(tir);
                    log.info("traceThirdInvokeRecordLog execute record success!");
                } catch (Exception e) {
                    log.error("traceThirdInvokeRecordLog-error:",e);
                }
            }
        });
    }

}
