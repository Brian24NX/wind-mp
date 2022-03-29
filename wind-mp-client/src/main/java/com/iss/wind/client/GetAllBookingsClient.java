package com.iss.wind.client;

import com.iss.wind.client.dto.auth.WindAccessTokenResp;
import com.iss.wind.client.dto.customerallbooking.GetAllBookingReq;
import com.iss.wind.client.dto.customerallbooking.GetAllBookingResp;
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
public class GetAllBookingsClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WindAuthClient windAuthClient;

    @Value("${com.iss.wind.digital-api-url}")
    private String digitalApiUrl;

    /**
     * API 全部登记信息
     * https://gitlab.cma-cgm.com/SOA/Cartography/-/blob/master/Swagger/Implementation/commercial/Service_SWAGGER_Commercial_booking.yaml
     * AllBookings
     * @return
     */
    public List<GetAllBookingResp> getAllBookings(GetAllBookingReq getAllBookingReq){
        String scope = "commercialbooking:read:be";
        String url = digitalApiUrl + "/commercial/shipment/shipmentrequest/v1/customers/"+getAllBookingReq.getCcgId()+"/bookings";
        WindAccessTokenResp accessToken = windAuthClient.getAccessToken(scope);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
        headers.add("scope", scope);
        HttpEntity request = new HttpEntity(headers);
        ParameterizedTypeReference<List<GetAllBookingResp>> responseType = new ParameterizedTypeReference<List<GetAllBookingResp>>() {};
        ResponseEntity<List<GetAllBookingResp>> response = restTemplate.exchange(url, HttpMethod.GET, request, responseType,getAllBookingReq);
        return response.getBody();
    }
}
