package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.RoutingFinderClient;
import com.iss.wind.client.ShipmentTrackingClient;
import com.iss.wind.client.dto.sechedule.RoutingFinderResp;
import com.iss.wind.client.dto.shipmenttracking.ShipmentTrackingResp;
import io.swagger.annotations.Api;
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
    public SimpleResult<List<ShipmentTrackingResp>> shipmentTracking(String shipmentRef) {
        List<ShipmentTrackingResp> routing = shipmentTrackingClient.shipmentTracking(shipmentRef);
        return SimpleResult.success(routing);
    }
}
