package com.iss.wind.common.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hanson
 * @date 2022/3/6  2:21
 */
@Slf4j
@Configuration
public class RestTemplateConfiguration {
    private String authToken;

    @Bean("authRestTemplate")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // // 构造加入interceptor	自己实现的interceptor
        // List<ClientHttpRequestInterceptor> interceptorList = new ArrayList<>();
        // interceptorList.add((request, body, execution) -> {
        //     if (authToken == null) {
        //         AccessToken accessToken = oAuthClient.getAccessToken();
        //         authToken = (accessToken.getTokenType() + " " + accessToken.getToken());
        //     }
        //
        //     HttpHeaders headers = request.getHeaders();
        //     headers.add("Authorization", authToken);
        //
        //     ClientHttpResponse response = execution.execute(request, body);
        //     if (response.getStatusCode().is4xxClientError()) {
        //         String errorCode = response.getHeaders().getFirst(oAuthClient.getErrorCodeHeader());
        //
        //         log.info("remote request [{}] error, errorCode : {}", request.getURI(), errorCode);
        //         if (errorCode != null) {
        //             // 这个⽅法会判断错误码，如果需要申请token，则返回true，否则返回false
        //             boolean needNewToken = oAuthClient.doRenewAccessTokenOnErrorCode(errorCode, false);
        //             if (needNewToken) {
        //                 // true =阻塞等待申请完毕，false=异步后台申请
        //                 oAuthClient.renewAccessToken(false);
        //
        //                 log.info("remote request [{}]  token expire, try reExecute", request.getURI(), errorCode);
        //
        //                 AccessToken accessToken = oAuthClient.getAccessToken();
        //                 authToken = (accessToken.getTokenType() + " " + accessToken.getToken());
        //                 response = execution.execute(request, body);
        //                 return response;
        //             }
        //         }
        //     }
        //     return response;
        // });
        //
        // InterceptingClientHttpRequestFactory interceptorFactory = new InterceptingClientHttpRequestFactory(
        //     new BufferingClientHttpRequestFactory(httpRequestFactory), interceptorList);
        // restTemplate.setRequestFactory(interceptorFactory);

        return restTemplate;
    }
}
