package com.iss.wind.client.configuration;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration("httpClientConfig")
@Slf4j
public class HttpClientConfig {

	@Bean
	public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory()
		throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		TrustStrategy acceptingTrustStrategy = (x509Certificates, authType) -> true;
		SSLContext sslContext = SSLContexts
			.custom()
			.loadTrustMaterial(null, acceptingTrustStrategy)
			.build();
		LayeredConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

		Registry<ConnectionSocketFactory> sfr = RegistryBuilder
			.<ConnectionSocketFactory>create()
			.register("http", PlainConnectionSocketFactory.getSocketFactory())
			.register("https", csf != null ? csf : SSLConnectionSocketFactory.getSocketFactory())
			.build();

		PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(sfr);
		// 最大连接数
		pollingConnectionManager.setMaxTotal(3000);
		// 单路由的并发数
		pollingConnectionManager.setDefaultMaxPerRoute(100);

		CloseableHttpClient httpClient = HttpClients
			.custom()
			.setSSLSocketFactory(csf)
			.setConnectionManager(pollingConnectionManager)
			.build();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setHttpClient(httpClient);
		factory.setConnectTimeout(5000); //获取连接超时
		factory.setReadTimeout(6000); //读超时
		factory.setConnectionRequestTimeout(60000); //请求超时

		return factory;
	}

	@Primary
	@Bean
	public RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory httpRequestFactory) {
		RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
		return restTemplate;
	}
}
