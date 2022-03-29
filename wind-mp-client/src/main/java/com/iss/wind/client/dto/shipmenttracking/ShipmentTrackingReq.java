package com.iss.wind.client.dto.shipmenttracking;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel("货物追踪请求类")
public class ShipmentTrackingReq {

    @ApiModelProperty(value = "运输号码",required = true)
    private String shipmentRef;

    @ApiModelProperty(value = "设备id",required = false)
    private String eqpid;
}
