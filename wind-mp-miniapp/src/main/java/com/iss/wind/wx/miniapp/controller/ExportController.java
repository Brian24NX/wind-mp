package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.common.util.log.WebLog;
import com.iss.wind.serevice.export.FreemarkerService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@Api(value = "模板导出")
@RestController
@Slf4j
public class ExportController {
    @Autowired
    private FreemarkerService freemarkerService;

    @GetMapping("/word-freemarker")
    @ApiOperation(value = "word模板导出",notes = "word模板导出")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "failedOrTimeOut")})
    @WebLog(description = "word-freemarker")
    public SimpleResult<String> word() throws UnsupportedEncodingException {
        return freemarkerService.expWord();
    }

}
