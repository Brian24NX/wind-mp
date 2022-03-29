package com.iss.wind.client;

import com.iss.wind.client.dto.auth.WindAccessTokenResp;
import com.iss.wind.client.dto.demurragedetention.DemurrageDetentionReq;
import com.iss.wind.client.dto.demurragedetention.DemurrageDetentionResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class DemurrageDetentionClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WindAuthClient windAuthClient;

    @Value("${com.iss.wind.digital-api-url}")
    private String digitalApiUrl;

    /**
     * API 滞期费用查询
     * https://gitlab.cma-cgm.com/SOA/Cartography/-/blob/master/Swagger/Implementation/commercial/Service_SWAGGER_Commercial_Pricing_v2.yaml
     * DemurrageDetention
     * @return
     */
    public List<DemurrageDetentionResp> demurrageDetention(DemurrageDetentionReq demurrageDetentionReq){
        String scope = "pricing:read:be";
        String url = digitalApiUrl + "/commercial/pricing/v2/detentionDemurrages/search";
        WindAccessTokenResp accessToken = windAuthClient.getAccessToken(scope);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
        headers.add("scope", scope);
        HttpEntity request = new HttpEntity(headers);
        ParameterizedTypeReference<List<DemurrageDetentionResp>> responseType = new ParameterizedTypeReference<List<DemurrageDetentionResp>>() {};
        ResponseEntity<List<DemurrageDetentionResp>> response = restTemplate.exchange(url, HttpMethod.GET, request, responseType,demurrageDetentionReq);
        return response.getBody();
    }
}
