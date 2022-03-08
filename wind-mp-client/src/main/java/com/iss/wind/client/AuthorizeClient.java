package com.iss.wind.client;

import com.iss.wind.client.dto.auth.SessionResponse;
import com.iss.wind.client.dto.auth.VerifyTokenReq;
import com.iss.wind.client.dto.auth.VerifyTokenResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hanson
 * @date 2022/3/3  16:19
 */
@Component
public class AuthorizeClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${com.ke.nts.gustav.session.host:https://api-cockpit.cma-cgm.com/}")
    private String sessionHost;

    public SessionResponse<VerifyTokenResp> verifyToken(VerifyTokenReq verifyTokenReq){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("source",verifyTokenReq.getSource());
        param.add("signature",verifyTokenReq.getSignature());
        param.add("token",verifyTokenReq.getToken());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(param, headers);
        SessionResponse<VerifyTokenResp> body = restTemplate.exchange(sessionHost+"/token/verify", HttpMethod.POST, request, new ParameterizedTypeReference<SessionResponse<VerifyTokenResp>>() {
        }).getBody();
        return body;
    }
}
