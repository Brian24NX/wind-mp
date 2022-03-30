package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.ShipmentTrackingClient;
import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingReq;
import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingResp;
import com.iss.wind.common.util.logutil.WebLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/shipment-tracking")
    @ApiOperation(value = "货物追踪查询",notes = "货物追踪查询")
    @ApiOperationSupport
    @WebLog(description = "货物追踪查询")
    public SimpleResult<ShipmentTrackingResp> shipmentTracking(ShipmentTrackingReq shipmentTrackingReq) {
        ShipmentTrackingResp result = shipmentTrackingClient.shipmentTracking(shipmentTrackingReq);
        return SimpleResult.success(result);
    }

//    @GetMapping("/test")
//    @ApiOperation(value = "测试",notes = "测试")
//    @ApiOperationSupport
//    @WebLog(description = "测试")
//    public String shipmentTracking1(ShipmentTrackingReq shipmentTrackingReq) {
//        log.info("contrl-param:"+shipmentTrackingReq.getShipmentRef()+":"+shipmentTrackingReq.getEqpid());
//        return "eewqe";
//    }

}
