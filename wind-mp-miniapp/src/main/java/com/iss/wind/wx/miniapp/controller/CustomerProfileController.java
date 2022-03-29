package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.CustomerProfileClient;
import com.iss.wind.client.dto.customerprofile.CustomerProfileReq;
import com.iss.wind.client.dto.customerprofile.CustomerProfileResp;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "用户简介")
@RestController
@Slf4j
public class CustomerProfileController {
    @Autowired
    private CustomerProfileClient customerProfileClient;

    @GetMapping("/customer-profile")
    public SimpleResult<CustomerProfileResp> getCustomerProfile(CustomerProfileReq customerProfileReq) {
        CustomerProfileResp result = customerProfileClient.getCustomerProfile(customerProfileReq);
        return SimpleResult.success(result);
    }
}
