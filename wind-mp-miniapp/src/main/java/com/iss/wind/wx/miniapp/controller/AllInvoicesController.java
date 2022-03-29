package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.GetAllInvoicesClient;
import com.iss.wind.client.dto.customerallinvoice.GetAllInvoiceReq;
import com.iss.wind.client.dto.customerallinvoice.GetAllInvoiceResp;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "全部发票信息")
@RestController
@Slf4j
public class AllInvoicesController {
    @Autowired
    private GetAllInvoicesClient getAllInvoicesClient;

    @GetMapping("/all-invoices")
    public SimpleResult<GetAllInvoiceResp> getAllInvoices(GetAllInvoiceReq getAllInvoiceReq) {
        GetAllInvoiceResp result = getAllInvoicesClient.getAllInvoices(getAllInvoiceReq);
        return SimpleResult.success(result);
    }
}
