package com.iss.wind.client.dto.pdf;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PdfReq {

    @ApiModelProperty(value = "货柜号",required = true)
    private String shipmentRef;
    @ApiModelProperty(value = "收件人",required = true)
    private String receiveMailAccount;
    @ApiModelProperty(value = "PDF文件访问路径",required = true)
    private String path;


}
