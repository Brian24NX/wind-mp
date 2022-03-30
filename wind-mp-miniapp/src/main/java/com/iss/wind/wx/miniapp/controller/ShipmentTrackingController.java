package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.ShipmentTrackingClient;
import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingReq;
import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingResp;
import com.iss.wind.common.util.log.WebLog;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Yves
 * @date 2022/3/25  9:26
 */
@Api(value = "货物追踪")
@RestController
@Slf4j
public class ShipmentTrackingController {
    @Autowired
    private ShipmentTrackingClient shipmentTrackingClient;

    @Qualifier(value = "restrTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/shipment-tracking")
    @ApiOperation(value = "货物追踪查询",notes = "货物追踪查询")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "请求异常或超时")})
    @WebLog(description = "货物追踪查询")
    public SimpleResult<ShipmentTrackingResp> shipmentTracking(ShipmentTrackingReq shipmentTrackingReq) {
        ShipmentTrackingResp result = shipmentTrackingClient.shipmentTracking(shipmentTrackingReq);
        return SimpleResult.success(result);
    }

    @GetMapping("/test")
    @ApiOperation(value = "测试",notes = "测试")
    @ApiResponses(value = {
            @ApiResponse(code = 504, message = "请求异常或超时"),
            @ApiResponse(code = 505, message = "操作失败")})
    @WebLog(description = "测试")
    public SimpleResult<String> shipmentTracking1() {
//        log.info("contrl-param:"+shipmentTrackingReq.getShipmentRef()+":"+shipmentTrackingReq.getEqpid());
        String url ="https://community-stg.unileverfoodsolutions.com.cn/api/v21/adam/11111doc.html";
//        String url ="https://www.baidu.com/";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("username", "admin");
        params.add("password", "adam123");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "");
        headers.add("scope", "");
        HttpEntity request = new HttpEntity(headers);
        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {};
//        ResponseEntity<String> resp = restTemplate.postForEntity(url,params,String.class);
        ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, request, responseType,params);;
        return SimpleResult.success("success");

//        try {
//            ResponseEntity<String> resp = restTemplate.postForEntity(url,params,String.class);
//            log.info("rep::::"+resp.getStatusCode()+":"+resp.getStatusCodeValue());//rep::::200 OK:200
//            if(200 == resp.getStatusCodeValue()) {
//                return SimpleResult.success("success");
//            }else {
//                return SimpleResult.fail("505","操作失败");
//            }
//        }catch (Exception e){
//            log.error("异常：",e);
//            return SimpleResult.fail("504","请求异常或超时");
//        }
    }

}
