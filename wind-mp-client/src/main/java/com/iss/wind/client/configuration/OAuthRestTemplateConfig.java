package com.iss.wind.client.configuration;

import com.iss.wind.client.WindAuthClient;
import com.iss.wind.client.util.SslUtils;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/**
 * @author Hanson
 * @date 2022/3/3  16:19
 */
@Slf4j
@Configuration
@DependsOn("httpClientConfig")
public class OAuthRestTemplateConfig {
	@Autowired
	private ClientHttpRequestFactory httpRequestFactory;

	@Bean
	public WindAuthClient windAuthClient(){
		return new WindAuthClient();
	}

	@Bean("authRestTemplate")
	public RestTemplate restTemplate(WindAuthClient windAuthClient) {
		RestTemplate restTemplate = new RestTemplate();

		// 构造加入interceptor	自己实现的interceptor
		List<ClientHttpRequestInterceptor> interceptorList = new ArrayList<>();
		interceptorList.add((request, body, execution) -> {
			windAuthClient.addToken(request);
			URI uri = request.getURI();
			if(uri.getPath().startsWith("https")){
				SslUtils.ignoreSsl();
			}
			ClientHttpResponse response = execution.execute(request, body);
			if (response.getStatusCode().is4xxClientError()) {
				HttpStatus errorCode = response.getStatusCode();
				log.info("remote request [{}] error, errorCode : {}", request.getURI(), errorCode.value());
				// 需要申请token
				if (HttpStatus.UNAUTHORIZED.value() == errorCode.value()) {
					log.info("remote request [{}] token expire, try reExecute", request.getURI());
					windAuthClient.addToken(request);
					response = execution.execute(request, body);
					return response;
				}
			}
			return response;
		});
		InterceptingClientHttpRequestFactory interceptorFactory = new InterceptingClientHttpRequestFactory(
			new BufferingClientHttpRequestFactory(httpRequestFactory), interceptorList);
		restTemplate.setRequestFactory(interceptorFactory);
		return restTemplate;
	}
}
