package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.GetAllBookingsClient;
import com.iss.wind.client.dto.customerallbooking.GetAllBookingReq;
import com.iss.wind.client.dto.customerallbooking.GetAllBookingResp;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "全部登记信息")
@RestController
@Slf4j
public class AllBookingsController {
    @Autowired
    private GetAllBookingsClient getAllBookingsClient;

    @GetMapping("/all-bookings")
    public SimpleResult<List<GetAllBookingResp>> getAllBookings(GetAllBookingReq getAllBookingReq) {
        List<GetAllBookingResp> result = getAllBookingsClient.getAllBookings(getAllBookingReq);
        return SimpleResult.success(result);
    }
}
