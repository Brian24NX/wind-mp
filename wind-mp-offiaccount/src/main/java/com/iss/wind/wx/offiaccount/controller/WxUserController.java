package com.iss.wind.wx.offiaccount.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hanson
 * @date 2022/3/13  13:51
 * 微信用户管理
 */
@RestController
@Slf4j
@Api(value = "微信公众号用户管理")
public class WxUserController {
    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/wx/user/get")
    public WxMpUserList getUser() throws WxErrorException {
        WxMpUserList wxMpUserList = wxMpService.getUserService().userList(null);
        return wxMpUserList;
    }
}
