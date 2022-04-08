package com.iss.wind.client.dto.sechedule;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RoutingFinderPostReq {

    @ApiModelProperty(value = "方案",required = true)
    private List<Integer> solutionNos;
    @ApiModelProperty(value = "航线数据",required = true)
    private List<RoutingFinderResp> routings;

    @ApiModelProperty(value = "排序方案",required = true)
    private List<Integer> sortSolutionNos;
    @ApiModelProperty(value = "是否直达",required = true)
    private boolean needDirectFlag;
    @ApiModelProperty(value = "排序类型；1离港 2到港 3运输时间",required = true)
    private int sortDateType;
    @ApiModelProperty(value = "是否最早到达",required = true)
    private boolean needEarlyFlag;



}
