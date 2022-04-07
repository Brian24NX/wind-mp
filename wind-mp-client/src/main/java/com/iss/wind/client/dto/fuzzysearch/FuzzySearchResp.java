package com.iss.wind.client.dto.fuzzysearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class FuzzySearchResp {

    @ApiModelProperty(value = "港口地")
    private Point point;
    private Country country;
    private String[] coordinates;
    private String pointType;
    private boolean assignedToRoute;
    private boolean hub;
    private boolean poi;
    private boolean pop;
    private String placeInternalId;

    @Data
    @Builder
    public static class Point {
        @ApiModelProperty(value = "港口编码")
        private String code;
        @ApiModelProperty(value = "港口名称")
        private String name;
    }

    @Data
    @Builder
    public static class Country {
        private String code;
        private String name;
    }
}
