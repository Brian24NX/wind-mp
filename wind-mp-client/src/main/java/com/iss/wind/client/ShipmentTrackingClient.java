package com.iss.wind.client;

import com.iss.wind.client.dto.auth.WindAccessTokenResp;
import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingReq;
import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

/**
 * @author Yves
 * @date 2022/3/25  11:26
 * Schedule API
 */
@Component
public class ShipmentTrackingClient {

    //@Qualifier(value = "restrTemplate")
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WindAuthClient windAuthClient;

    @Value("${com.iss.wind.digital-api-url}")
    private String digitalApiUrl;

    /**
     * API 获取货物追踪
     * https://gitlab.cma-cgm.com/SOA/Cartography/-/blob/master/Swagger/Implementation/logistic.tracking.v1.srv.yaml
     * Shipment Tracking
     * @return
     */
    public ShipmentTrackingResp shipmentTracking(ShipmentTrackingReq shipmentTrackingReq){
        String scope = "commercialmoves:be";
        String shipmentRef = shipmentTrackingReq.getShipmentRef();
        String url = digitalApiUrl + "/logistic/tracking/v1/shipments/"+shipmentRef+"/equipments/moves/commercialCycle";
        WindAccessTokenResp accessToken = windAuthClient.getAccessToken(scope);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
        headers.add("scope", scope);
        HttpEntity request = new HttpEntity(headers);
        ParameterizedTypeReference<ShipmentTrackingResp> responseType = new ParameterizedTypeReference<ShipmentTrackingResp>() {};
        ResponseEntity<ShipmentTrackingResp> response = restTemplate.exchange(url, HttpMethod.GET, request, responseType,shipmentTrackingReq);
        return response.getBody();
    }
}
