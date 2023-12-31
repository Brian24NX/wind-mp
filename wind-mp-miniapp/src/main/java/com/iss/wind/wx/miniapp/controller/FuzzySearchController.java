package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.FuzzySearchClient;
import com.iss.wind.client.dto.fuzzysearch.FuzzySearchResp;
import com.iss.wind.common.util.ValUtils;
import com.iss.wind.common.util.log.WebLog;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    public SimpleResult<List<Map>> fuzzySearch(@ApiParam(name = "searchStr" ,value = "搜索字符" ,required = true) @RequestParam String searchStr) {
        List<FuzzySearchResp> ret = fuzzySearchClient.fuzzySearch(searchStr);
        //组装处理
        return SimpleResult.success(fuzzySearchClient.handle(ret));
    }
}
