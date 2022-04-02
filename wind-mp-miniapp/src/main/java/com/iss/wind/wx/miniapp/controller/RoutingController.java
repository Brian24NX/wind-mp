package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.RoutingFinderClient;
import com.iss.wind.client.dto.sechedule.RoutingFinderResp;
import com.iss.wind.common.util.log.WebLog;
import io.swagger.annotations.*;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hanson
 * @date 2022/3/23  9:26
 */
@Api(value = "航线信息")
@RestController
@Slf4j
public class RoutingController {
    @Autowired
    private RoutingFinderClient routingFinderClient;

    @GetMapping("/routing-finder")
    @ApiOperation(value = "航线信息查询",notes = "航线信息查询")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "failedOrTimeOut")})
    @WebLog(description = "routing-finder")
    public SimpleResult<List<RoutingFinderResp>> routings(@ApiParam(name = "placeOfLoading" ,value = "启航港" ,required = true) @RequestParam String placeOfLoading,
                                                          @ApiParam(name = "placeOfDischarge" ,value = "目的港" ,required = true) @RequestParam String placeOfDischarge,
                                                          @ApiParam(name = "specificRoutings" ,value = "公司方案" ) @RequestParam String[] specificRoutings,
                                                          @ApiParam(name = "shippingCompany" ,value = "轮船公司" ) @RequestParam String shippingCompany,
                                                          @ApiParam(name = "departureDate" ,value = "离港日期" ) @RequestParam String departureDate,
                                                          @ApiParam(name = "arrivalDate" ,value = "预计到达日期" ) @RequestParam String arrivalDate,
                                                          @ApiParam(name = "searchRange" ,value = "搜寻天数" ) @RequestParam String searchRange
                                                          ) {
        List<RoutingFinderResp> routing = routingFinderClient.routings(placeOfLoading, placeOfDischarge,specificRoutings,shippingCompany,departureDate,arrivalDate,searchRange);
        return SimpleResult.success(routing);
    }
}
