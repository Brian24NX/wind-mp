package com.iss.wind.client;

import com.iss.wind.client.dto.auth.WindAccessTokenResp;
import com.iss.wind.client.dto.customerallinvoice.GetAllInvoiceReq;
import com.iss.wind.client.dto.customerallinvoice.GetAllInvoiceResp;
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
public class GetAllInvoicesClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WindAuthClient windAuthClient;

    @Value("${com.iss.wind.digital-api-url}")
    private String digitalApiUrl;

    /**
     * API 全部发票信息
     * https://gitlab.cma-cgm.com/SOA/Cartography/-/blob/master/Swagger/Implementation/commercial/Service_SWAGGER_Commercial_invoice.yaml
     * AllInvoices
     * @return
     */
    public GetAllInvoiceResp getAllInvoices(GetAllInvoiceReq getAllInvoiceReq){
        String scope = "customershippinginvoice:read:be";
        String url = digitalApiUrl + "commercial/shipment/invoice/v1/customers/"+getAllInvoiceReq.getParameters().get(0).getCcgId()+"/invoices";
        WindAccessTokenResp accessToken = windAuthClient.getAccessToken(scope);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
        headers.add("scope", scope);
        HttpEntity request = new HttpEntity(headers);
        ParameterizedTypeReference<GetAllInvoiceResp> responseType = new ParameterizedTypeReference<GetAllInvoiceResp>() {};
        ResponseEntity<GetAllInvoiceResp> response = restTemplate.exchange(url, HttpMethod.GET, request, responseType,getAllInvoiceReq);
        return response.getBody();
    }
}
