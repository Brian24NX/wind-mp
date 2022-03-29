package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.DemurrageDetentionClient;
import com.iss.wind.client.dto.demurragedetention.DemurrageDetentionReq;
import com.iss.wind.client.dto.demurragedetention.DemurrageDetentionResp;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "滞期费用查询")
@RestController
@Slf4j
public class DemurrageDetentionController {
    @Autowired
    private DemurrageDetentionClient demurrageDetentionClient;

    @GetMapping("/demurrageDetention")
    public SimpleResult<List<DemurrageDetentionResp>> demurrageDetention(DemurrageDetentionReq demurrageDetentionReq) {
        List<DemurrageDetentionResp> result = demurrageDetentionClient.demurrageDetention(demurrageDetentionReq);
        return SimpleResult.success(result);
    }
}
