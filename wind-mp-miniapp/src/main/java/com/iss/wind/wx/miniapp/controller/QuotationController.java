package com.iss.wind.wx.miniapp.controller;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.SearchQuotationClient;
import com.iss.wind.client.SubmitQuotationClient;
import com.iss.wind.client.dto.searchquotations.SearchQuotationsReq;
import com.iss.wind.client.dto.searchquotations.SearchQuotationsResp;
import com.iss.wind.client.dto.submitquotations.SubmitQuotationsReq;
import com.iss.wind.client.dto.submitquotations.SubmitQuotationsResp;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "价目信息")
@RestController
@Slf4j
public class QuotationController {

    @Autowired
    private SearchQuotationClient searchQuotationClient;
    @Autowired
    private SubmitQuotationClient submitQuotationClient;

    /**
     *  价目查询
     * @param searchQuotationsReq
     * @return
     */
    @GetMapping("/searchQuotation")
    public SimpleResult<SearchQuotationsResp> searchQuotation(SearchQuotationsReq searchQuotationsReq) {
        SearchQuotationsResp result = searchQuotationClient.searchQuotation(searchQuotationsReq);
        return SimpleResult.success(result);
    }

    /**
     *  价目提交
     * @param submitQuotationsReq
     * @return
     */
    @GetMapping("/submitQuotation")
    public SimpleResult<SubmitQuotationsResp> submitQuotation(SubmitQuotationsReq submitQuotationsReq) {
        SubmitQuotationsResp result = submitQuotationClient.submitQuotation(submitQuotationsReq);
        return SimpleResult.success(result);
    }

}
