package com.iss.wind.client;

import com.iss.wind.client.dto.auth.WindAccessTokenResp;
import com.iss.wind.client.dto.customerprofile.CustomerProfileReq;
import com.iss.wind.client.dto.customerprofile.CustomerProfileResp;
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
public class CustomerProfileClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WindAuthClient windAuthClient;

    @Value("${com.iss.wind.digital-api-url}")
    private String digitalApiUrl;

    /**
     * API 用户简介
     * https://gitlab.cma-cgm.com/SOA/Cartography/-/blob/master/Swagger/Virtualization/commercial.customer.profile.v2.yaml
     * CustomerProfile
     * @return
     */
    public CustomerProfileResp getCustomerProfile(CustomerProfileReq customerProfileReq){
        String scope = "customerprofile:read:fe";
        String url = digitalApiUrl + "/commercial/customer/profile/v2/profiles/"+customerProfileReq.getCcgId();
        WindAccessTokenResp accessToken = windAuthClient.getAccessToken(scope);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
        headers.add("scope", scope);
        HttpEntity request = new HttpEntity(headers);
        ParameterizedTypeReference<CustomerProfileResp> responseType = new ParameterizedTypeReference<CustomerProfileResp>(){};
        ResponseEntity<CustomerProfileResp> response = restTemplate.exchange(url, HttpMethod.GET, request, responseType,customerProfileReq);
        return response.getBody();
    }
}
