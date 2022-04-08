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

import java.util.List;
import java.util.Map;

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

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/shipment-tracking")
    @ApiOperation(value = "货物追踪查询",notes = "货物追踪查询")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "failedOrTimeOut")})
    @WebLog(description = "shipment-tracking")
    public SimpleResult<List<Map>> shipmentTracking(ShipmentTrackingReq shipmentTrackingReq) {
        List<Map> ret = shipmentTrackingClient.shipmentTracking(shipmentTrackingReq);
        return null == ret?SimpleResult.fail("404","未查询到数据"):SimpleResult.success(ret);
    }

}
