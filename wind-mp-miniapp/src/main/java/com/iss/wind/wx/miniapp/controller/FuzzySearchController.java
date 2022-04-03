package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.FuzzySearchClient;
import com.iss.wind.client.dto.fuzzysearch.FuzzySearchResp;
import com.iss.wind.common.util.log.WebLog;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "模糊查询")
@RestController
@Slf4j
public class FuzzySearchController {
    @Autowired
    private FuzzySearchClient fuzzySearchClient;

    @GetMapping("/fuzzySearch")
    @ApiOperation(value = "模糊查询",notes = "模糊查询")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "failedOrTimeOut")})
    @WebLog(description = "fuzzySearch")
    public SimpleResult<List<FuzzySearchResp>> fuzzySearch(@ApiParam(name = "codeStarts" ,value = "启航港" ,required = true) @RequestParam String codeStarts
//                                                        @ApiParam(name = "placeOfDischarge" ,value = "目的港" ,required = true) @RequestParam String placeOfDischarge,
//                                                        @ApiParam(name = "specificRoutings" ,value = "公司方案" ) @RequestParam String[] specificRoutings,
//                                                        @ApiParam(name = "shippingCompany" ,value = "轮船公司" ) @RequestParam String shippingCompany
                                                          ) {
        List<FuzzySearchResp> routing = fuzzySearchClient.fuzzySearch(codeStarts);
        return SimpleResult.success(routing);
    }
}
