package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.ChargeFinderTariffClient;
import com.iss.wind.client.dto.chargefindertariff.ChargeFinderTariffReq;
import com.iss.wind.client.dto.chargefindertariff.ChargeFinderTariffResp;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "关税查询")
@RestController
@Slf4j
public class ChargeFinderTariffController {
    @Autowired
    private ChargeFinderTariffClient chargeFinderTariffClient;

    @GetMapping("/chargeFinderTariff")
    public SimpleResult<List<ChargeFinderTariffResp>> chargeFinderTariff(ChargeFinderTariffReq chargeFinderTariffReq) {
        List<ChargeFinderTariffResp> result = chargeFinderTariffClient.chargeFinderTariff(chargeFinderTariffReq);
        return SimpleResult.success(result);
    }
}
