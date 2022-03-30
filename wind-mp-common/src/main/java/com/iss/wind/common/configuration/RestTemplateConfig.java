package com.iss.wind.common.configuration;

import com.iss.wind.common.util.rest.RestTemplateLogInterceptor;
import com.iss.wind.common.util.rest.ThrowErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.TemporalUnit;


// restTemplate配置类
@Configuration
public class RestTemplateConfig {

    @Autowired
    private RestTemplateLogInterceptor restTemplateLogInterceptor;

    @Bean(name = "restrTemplate")
    public RestTemplate restTemplate() {

        //restTemplate 最大超时 = setConnectTimeout + setReadTimeout
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder
                // 设置http请求连接超时时间
                .setConnectTimeout(Duration.ofSeconds(8))
                // 设置http请求读数据超时时间
                .setReadTimeout(Duration.ofSeconds(8))
                // 自定义拦截器
                .interceptors(restTemplateLogInterceptor)
                // 自定义异常处理
                .errorHandler(new ThrowErrorHandler())
                .build();
    }
}