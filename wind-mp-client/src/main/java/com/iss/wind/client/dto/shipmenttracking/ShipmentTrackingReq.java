package com.iss.wind.client.dto.shipmenttracking;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@ApiModel("货物追踪请求类")
public class ShipmentTrackingReq {

    @NotNull(message="运输号码不可为空！")
    //@Pattern(regexp = "/^(?![^A-Za-z]+$)(?![^0-9]+$)[\\x21-x7e]{8,12}$/" , message = "运输号码有误")
    @ApiModelProperty(value = "运输号码",required = true)
    private String shipmentRef;

    @ApiModelProperty(value = "设备id",required = false)
    private String eqpid;
}
