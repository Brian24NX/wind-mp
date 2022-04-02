package com.iss.wind.client;

import com.iss.wind.client.dto.auth.WindAccessTokenResp;
import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingReq;
import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingResp;
import com.iss.wind.client.util.StrUtils;
import com.iss.wind.client.util.rest.RestTemplateLogInterceptor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Map;

/**
 * @author Yves
 * @date 2022/3/25  11:26
 * Schedule API
 */
@Slf4j
@Component
public class ShipmentTrackingClient {

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
     * @return StrUtils.isBlank(eqpid)? shipmentUrl:containerUrl
     */
    public Map shipmentTracking(ShipmentTrackingReq shipmentTrackingReq){
        Map ret = new HashMap();
        ShipmentTrackingResp shipmentTrackingResp;
        String shipArrStr = shipmentTrackingReq.getShipmentRef();
        String[] shipArr = shipArrStr.split(",");
        int len = shipArr.length;
        if(len == 1){ //场景一，shipmentRef:[货柜号]   一个卡片信息返回
            String shipment = shipArr[0];
            shipmentTrackingResp = getShipment(shipment,shipmentTrackingReq);
            ret.put(shipment+"",null == shipmentTrackingResp ? "":shipmentTrackingResp);
            return ret;
        }else if(len == 2){//场景二，shipmentRef:[货柜号,运输号]   两个卡片信息返回
            String shipment = shipArr[0];
            shipmentTrackingResp = getShipment(shipment,shipmentTrackingReq);
            ret.put(shipment+"",null == shipmentTrackingResp ? "":shipmentTrackingResp);
            String shipment1 = shipArr[1];
            shipmentTrackingResp = getShipment(shipment1,shipmentTrackingReq);
            ret.put(shipment1+"",null == shipmentTrackingResp ? "":shipmentTrackingResp);
            return ret;
        }else if(len == 3){//场景三、四、五，shipmentRef:[货柜号,货柜号,运输号]  每个卡片信息，存在的返回输出货柜号信息，查询不到返回不存在
            String shipment = shipArr[0];
            shipmentTrackingResp = getShipment(shipment,shipmentTrackingReq);
            ret.put(shipment+"",null == shipmentTrackingResp ? "":shipmentTrackingResp);
            String shipment1 = shipArr[1];
            shipmentTrackingResp = getShipment(shipment1,shipmentTrackingReq);
            ret.put(shipment1+"",null == shipmentTrackingResp ? "":shipmentTrackingResp);
            String shipment2 = shipArr[2];
            shipmentTrackingResp = getShipment(shipment2,shipmentTrackingReq);
            ret.put(shipment2+"",null == shipmentTrackingResp ? "":shipmentTrackingResp);
            return ret;
        }
        return null;
    }

    public ResponseEntity<ShipmentTrackingResp> handleSipments(String url,String scope,Map<String,Object> paramMap ){
        WindAccessTokenResp accessToken = windAuthClient.getAccessToken(scope);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
        headers.add("scope", scope);
        HttpEntity request = new HttpEntity(headers);
        ParameterizedTypeReference<ShipmentTrackingResp> responseType = new ParameterizedTypeReference<ShipmentTrackingResp>() {};
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~handleSipments-url={"+url+"}~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        ResponseEntity<ShipmentTrackingResp> response = null;
        try {
            restTemplate.getInterceptors().add(new RestTemplateLogInterceptor());
            response = restTemplate.exchange(url, HttpMethod.GET, request, responseType, paramMap);
            log.error("handleSipments-response:",response);
            restTemplate.getInterceptors().clear();
        }catch (Exception e){
            log.error("handleSipments-exception:",e);
        }
        return response;
    }


    public ShipmentTrackingResp getShipment(String shipment,ShipmentTrackingReq shipmentTrackingReq){
        String scope = "commercialmoves:be";
        String baseUrlMove = digitalApiUrl + "/logistic/tracking/v1/equipments/";
        String baseUrlMent = digitalApiUrl + "/logistic/tracking/v1/shipments/";
        String endPointMove = "/moves/lastCycle";
        String endPointMent = "/equipments/moves/commercialCycle";
        //优先查集装箱
        String url = baseUrlMove + shipment + endPointMove;
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("eqpId",shipment);
        ResponseEntity<ShipmentTrackingResp> response = handleSipments(url,scope,paramMap);
        ShipmentTrackingResp shipmentTrackingResp;
        if (null != response && response.getStatusCodeValue() == 200){
            shipmentTrackingResp = response.getBody();
        }else {
            url = baseUrlMent + shipment + endPointMent;
            paramMap = new HashMap<>();
            paramMap.put("shipmentRef",shipment);
            response = handleSipments(url,scope,paramMap);
            if(null != response && response.getStatusCodeValue() == 200){
                shipmentTrackingResp = response.getBody();
            }else {
                shipmentTrackingResp = null;
            }
        }
        return shipmentTrackingResp;
    }

}
