package com.iss.wind.client;

import com.iss.wind.client.dto.auth.WindAccessTokenResp;
import com.iss.wind.client.util.SslUtils;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    private HashMap<String,WindAccessTokenResp> accessTokenMap = new HashMap<>();

    @Autowired
    private RestTemplate restTemplate;

    public WindAccessTokenResp getAccessToken(String scope){
        if(accessTokenMap.containsKey(scope)){
            return accessTokenMap.get(scope);
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
            SslUtils.ignoreSsl();
        }
        WindAccessTokenResp accessTokenResp = restTemplate.postForObject(clientUrl, entity, WindAccessTokenResp.class);
        accessTokenMap.put(scope,accessTokenResp);
        return accessTokenResp;
    }


    public void addToken(HttpRequest request) {
        String scope = request.getHeaders().getFirst("scope");
        WindAccessTokenResp accessToken = getAccessToken(scope);
        HttpHeaders headers = request.getHeaders();
        String access_token = accessToken.getAccess_token();
        String token_type = accessToken.getToken_type();
        headers.add("Authorization",access_token +" "+token_type);
    }

}
