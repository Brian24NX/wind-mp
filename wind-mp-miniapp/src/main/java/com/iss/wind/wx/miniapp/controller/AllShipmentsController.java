package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.GetAllShipmentsClient;
import com.iss.wind.client.dto.customerallshipment.GetAllShipmentReq;
import com.iss.wind.client.dto.customerallshipment.GetAllShipmentResp;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "全部货物信息")
@RestController
@Slf4j
public class AllShipmentsController {
    @Autowired
    private GetAllShipmentsClient getAllShipmentsClient;

    @GetMapping("/all-shipments")
    public SimpleResult<List<GetAllShipmentResp>> getAllShipments(GetAllShipmentReq getAllShipmentReq) {
        List<GetAllShipmentResp> result = getAllShipmentsClient.getAllShipments(getAllShipmentReq);
        return SimpleResult.success(result);
    }
}
