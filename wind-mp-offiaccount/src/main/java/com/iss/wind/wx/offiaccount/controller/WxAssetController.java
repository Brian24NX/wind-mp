package com.iss.wind.wx.offiaccount.controller;

import io.swagger.annotations.Api;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialArticleUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hanson
 * @date 2022/3/13  13:51
 * 微信素材管理
 */
@RestController
@Slf4j
@Api(value = "微信公众号素材管理")
public class WxAssetController {
    @Autowired
    private WxMpService wxMpService;

    /**
     * 微信新增临时素材
     */
    @PostMapping("/wx/asset/uploadImg")
    public String mediaUpload() throws WxErrorException {
        File file = new File("F://wx-img.jpg");
        WxMediaImgUploadResult wxMediaImgUploadResult = wxMpService.getMaterialService().mediaImgUpload(file);
        return wxMediaImgUploadResult.getUrl();
    }

    /**
     * 微信新增永久素材
     */
    @PostMapping("/wx/asset/newUpdate")
    public String materialNewsUpdate() throws WxErrorException {
        WxMpMaterialArticleUpdate material = new WxMpMaterialArticleUpdate();
        wxMpService.getMaterialService().materialNewsUpdate(material);
        String mediaId = material.getMediaId();
        return mediaId;
    }
}
