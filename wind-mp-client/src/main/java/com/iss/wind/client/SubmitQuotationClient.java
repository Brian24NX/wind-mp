package com.iss.wind.client;

import com.iss.wind.client.dto.auth.WindAccessTokenResp;
import com.iss.wind.client.dto.submitquotations.SubmitQuotationsReq;
import com.iss.wind.client.dto.submitquotations.SubmitQuotationsResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SubmitQuotationClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WindAuthClient windAuthClient;

    @Value("${com.iss.wind.digital-api-url}")
    private String digitalApiUrl;

    /**
     * API 价目提交
     * https://gitlab.cma-cgm.com/SOA/Cartography/-/blob/master/Swagger/Implementation/commercial/Service_SWAGGER_Commercial_Pricing_v2.yaml
     * searchQuotation
     * @return
     */
    public SubmitQuotationsResp submitQuotation(SubmitQuotationsReq searchQuotationsReq){
        String scope = "pricing:write:be";
        String url = digitalApiUrl + "/commercial/pricing/v2/quotation/create";
        WindAccessTokenResp accessToken = windAuthClient.getAccessToken(scope);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
        headers.add("scope", scope);
        HttpEntity request = new HttpEntity(headers);
        ParameterizedTypeReference<SubmitQuotationsResp> responseType = new ParameterizedTypeReference<SubmitQuotationsResp>(){};
        ResponseEntity<SubmitQuotationsResp> response = restTemplate.exchange(url, HttpMethod.GET, request, responseType,searchQuotationsReq);
        return response.getBody();
    }
}
