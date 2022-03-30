package com.iss.wind.client.dto.shipmenttracking;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
@Builder
@ApiModel("货物追踪请求类")
public class ShipmentTrackingReq {

    @NotNull(message="运输号码不可为空！")
    @ApiModelProperty(value = "运输号码",required = true)
    private String shipmentRef;

    @ApiModelProperty(value = "设备id",required = false)
    private String eqpid;
}
