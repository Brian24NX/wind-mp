package com.iss.wind.client;

import com.iss.wind.client.dto.auth.WindAccessTokenResp;
import com.iss.wind.client.dto.chargefindertariff.ChargeFinderTariffReq;
import com.iss.wind.client.dto.chargefindertariff.ChargeFinderTariffResp;
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
public class ChargeFinderTariffClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WindAuthClient windAuthClient;

    @Value("${com.iss.wind.digital-api-url}")
    private String digitalApiUrl;

    /**
     * API 关税查询
     * https://gitlab.cma-cgm.com/SOA/Cartography/-/blob/master/Swagger/Implementation/commercial/Service_SWAGGER_Commercial_Tariff.yaml
     * ChargeFinderTariff
     * @return
     */
    public List<ChargeFinderTariffResp> chargeFinderTariff(ChargeFinderTariffReq chargeFinderTariffReq){
        String scope = "commercialmaritimetarrif:read:be";
        String url = digitalApiUrl + "/pricing/tariff/maritime/v1/maritimetariffs";
        WindAccessTokenResp accessToken = windAuthClient.getAccessToken(scope);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
        headers.add("scope", scope);
        HttpEntity request = new HttpEntity(headers);
        ParameterizedTypeReference<List<ChargeFinderTariffResp>> responseType = new ParameterizedTypeReference<List<ChargeFinderTariffResp>>() {};
        ResponseEntity<List<ChargeFinderTariffResp>> response = restTemplate.exchange(url, HttpMethod.GET, request, responseType,chargeFinderTariffReq);
        return response.getBody();
    }
}
