package com.iss.wind.client;

import com.iss.wind.client.dto.auth.WindAccessTokenResp;
import com.iss.wind.client.dto.customerallshipment.GetAllShipmentReq;
import com.iss.wind.client.dto.customerallshipment.GetAllShipmentResp;
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
public class GetAllShipmentsClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WindAuthClient windAuthClient;

    @Value("${com.iss.wind.digital-api-url}")
    private String digitalApiUrl;

    /**
     * API 全部货物信息
     * https://api-cockpit.cma-cgm.com/explore/catalog/commercial/api/commercial.customer.shipment.v2/swagger
     * AllShipments
     * @return
     */
    public List<GetAllShipmentResp> getAllShipments(GetAllShipmentReq getAllShipmentReq){
        String scope = "customershipment:read:fe";
        String url = digitalApiUrl + "/commercial/customer/shipment/v2/customers/"+getAllShipmentReq.getCcgId()+"/shipmentsLight";
        WindAccessTokenResp accessToken = windAuthClient.getAccessToken(scope);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
        headers.add("scope", scope);
        HttpEntity request = new HttpEntity(headers);
        ParameterizedTypeReference<List<GetAllShipmentResp>> responseType = new ParameterizedTypeReference<List<GetAllShipmentResp>>() {};
        ResponseEntity<List<GetAllShipmentResp>> response = restTemplate.exchange(url, HttpMethod.GET, request, responseType,getAllShipmentReq);
        return response.getBody();
    }
}
