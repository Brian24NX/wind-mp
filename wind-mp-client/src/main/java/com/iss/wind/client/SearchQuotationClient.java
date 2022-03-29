package com.iss.wind.client;

import com.iss.wind.client.dto.auth.WindAccessTokenResp;
import com.iss.wind.client.dto.searchquotations.SearchQuotationsReq;
import com.iss.wind.client.dto.searchquotations.SearchQuotationsResp;
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
public class SearchQuotationClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WindAuthClient windAuthClient;

    @Value("${com.iss.wind.digital-api-url}")
    private String digitalApiUrl;

    /**
     * API 价目查询
     * https://gitlab.cma-cgm.com/SOA/Cartography/-/blob/master/Swagger/Implementation/commercial/Service_SWAGGER_Commercial_Pricing_v2.yaml
     * searchQuotation
     * @return
     */
    public SearchQuotationsResp searchQuotation(SearchQuotationsReq searchQuotationsReq){
        String scope = "pricing:read:be";
        String url = digitalApiUrl + "/commercial/pricing/v2/quoteLines/search";
        WindAccessTokenResp accessToken = windAuthClient.getAccessToken(scope);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
        headers.add("scope", scope);
        HttpEntity request = new HttpEntity(headers);
        ParameterizedTypeReference<SearchQuotationsResp> responseType = new ParameterizedTypeReference<SearchQuotationsResp>(){};
        ResponseEntity<SearchQuotationsResp> response = restTemplate.exchange(url, HttpMethod.GET, request, responseType,searchQuotationsReq);
        return response.getBody();
    }
}
