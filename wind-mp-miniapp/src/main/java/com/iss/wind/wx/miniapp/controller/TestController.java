package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.common.bo.UserInfoBo;
import com.iss.wind.common.util.log.WebLog;
import com.iss.wind.serevice.export.FreemarkerService;
import com.iss.wind.serevice.impl.RedisService;
import com.iss.wind.serevice.impl.UserInfoTestServiceImpl;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@Api(value = "测试用户")
public class TestController {

    @Autowired
    private FreemarkerService freemarkerService;
    @Autowired
    private UserInfoTestServiceImpl wxMpService;
    @Autowired
    private RedisService redisService;

    @GetMapping("/setUser")
    @ApiOperation(value = "测试用户set",notes = "测试用户set")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "failedOrTimeOut")})
    @WebLog(description = "setUser")
    public SimpleResult<UserInfoBo> setUser(
            @ApiParam(name = "id" ,value = "id" ,required = true) @RequestParam Long id
    ) throws WxErrorException {
        redisService.setValue("user", 123);
        UserInfoBo user = wxMpService.get(id);
        return SimpleResult.success(user);
    }

    @GetMapping("/getUser")
    @ApiOperation(value = "测试用户get",notes = "测试用户get")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "failedOrTimeOut")})
    @WebLog(description = "getUser")
    public SimpleResult<Object> getUser(
            @ApiParam(name = "id" ,value = "id" ,required = true) @RequestParam Long id
    ) throws WxErrorException {
        return SimpleResult.success(redisService.getValue("user"));
    }

//    @GetMapping("/export-freemarker")
//    @ApiOperation(value = "模板导出",notes = "模板导出")
//    @ApiResponses(value = {@ApiResponse(code = 500, message = "failedOrTimeOut")})
//    @WebLog(description = "export-freemarker")
//    public SimpleResult<List<String>> word() throws UnsupportedEncodingException {
//        return freemarkerService.expWord();
//    }

//    @GetMapping("/export")
//    @ApiOperation(value = "pdf下载", notes = "pdf下载")
//    public void errExcel(HttpServletResponse response) throws Exception {
//         freemarkerService.exportFile(response);
//
//    }

}
