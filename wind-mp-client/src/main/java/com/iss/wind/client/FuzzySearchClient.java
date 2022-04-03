package com.iss.wind.client;

import com.iss.wind.client.dto.auth.WindAccessTokenResp;
import com.iss.wind.client.dto.fuzzysearch.FuzzySearchResp;
import com.iss.wind.client.util.rest.RestTemplateLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FuzzySearchClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WindAuthClient windAuthClient;

    @Value("${com.iss.wind.digital-api-url}")
    private String digitalApiUrl;

    /**
     * API 模糊查询
     * https://gitlab.cma-cgm.com/SOA/Cartography/-/blob/master/Swagger/Implementation/referential.location.v3.srv.yaml
     * FuzzySearch
     * @return
     */
    public List<FuzzySearchResp> fuzzySearch(String codeStarts){
        String scope = "location:be";
        String url = digitalApiUrl + "/referential/location/v3/points/light";
        WindAccessTokenResp accessToken = windAuthClient.getAccessToken(scope);
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("codeStarts",codeStarts);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
        headers.add("scope", scope);
        HttpEntity request = new HttpEntity(headers);
        ParameterizedTypeReference<List<FuzzySearchResp>> responseType = new ParameterizedTypeReference<List<FuzzySearchResp>>() {};
        restTemplate.getInterceptors().add(new RestTemplateLogInterceptor());
        ResponseEntity<List<FuzzySearchResp>> response = restTemplate.exchange(url, HttpMethod.GET, request, responseType,paramMap);
        restTemplate.getInterceptors().clear();
        return response.getBody();
    }
}
