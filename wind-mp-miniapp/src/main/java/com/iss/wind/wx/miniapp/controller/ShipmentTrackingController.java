package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.ShipmentTrackingClient;
import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingReq;
import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public SimpleResult<ShipmentTrackingResp> shipmentTracking(ShipmentTrackingReq shipmentTrackingReq) {
        System.out.println("contrl-param:"+shipmentTrackingReq.getShipmentRef()+":"+shipmentTrackingReq.getEqpid());
        ShipmentTrackingResp result = shipmentTrackingClient.shipmentTracking(shipmentTrackingReq);
        return SimpleResult.success(result);
    }
}
