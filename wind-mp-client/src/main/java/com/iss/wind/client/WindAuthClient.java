package com.iss.wind.client;

import com.iss.wind.client.dto.auth.WindAccessTokenResp;
import com.iss.wind.client.util.HttpUtils;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hanson
 * @date 2022/3/21  12:14
 */
@Slf4j
public class WindAuthClient {
    @Value("${com.iss.wind.client.url}")
    private String clientUrl;

    @Value("${com.iss.wind.client.id}")
    private String clientId;

    @Value("${com.iss.wind.client.secret:BfrPuRj81lKyVL2hCIewCAHMUkGbEqnyTAsVoszX8zVm5U8ejDEZ33Gt3VDFkWf2}")
    private String clientSecret;

    private ConcurrentHashMap<String,WindAccessTokenResp> accessTokenMap = new ConcurrentHashMap<>();

    //@Qualifier(value = "restrTemplate")
    @Autowired
    private RestTemplate restTemplate;

    public WindAccessTokenResp getAccessToken(String scope){
        if(accessTokenMap.containsKey(scope)){
            WindAccessTokenResp accessTokenResp = accessTokenMap.get(scope);
            long expires = System.currentTimeMillis() - accessTokenResp.getGenMillisecond();
            log.warn("scope:{} genMillisecond:{} expiresIn:{} expires:{}",scope,accessTokenResp.getGenMillisecond(),accessTokenResp.getExpiresIn(),expires);
            //未过期，返回
            if(expires < Long.parseLong(accessTokenResp.getExpiresIn()) * 1000){
                return accessTokenMap.get(scope);
            }
            //已过期移除,重新获取
            log.warn("scope:{} expire",scope);
        }
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("grant_type", "client_credentials");
        postParameters.add("scope", scope);
        postParameters.add("client_id", clientId);
        postParameters.add("client_secret", clientSecret);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(postParameters, headers);
        if(clientUrl.startsWith("https")){
            HttpUtils.ignoreSsl();
        }
        WindAccessTokenResp accessTokenResp = restTemplate.postForObject(clientUrl, entity, WindAccessTokenResp.class);
        accessTokenResp.setGenMillisecond(System.currentTimeMillis());
        accessTokenMap.put(scope,accessTokenResp);
        return accessTokenResp;
    }


    public void addToken(HttpRequest request) {
        String scope = request.getHeaders().getFirst("scope");
        WindAccessTokenResp accessToken = getAccessToken(scope);
        HttpHeaders headers = request.getHeaders();
        headers.add("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
        headers.add("Host", HttpUtils.getIpAddress());
    }

}
