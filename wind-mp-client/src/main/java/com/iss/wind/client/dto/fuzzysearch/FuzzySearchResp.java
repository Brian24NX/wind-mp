package com.iss.wind.client.dto.fuzzysearch;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class FuzzySearchResp {

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
        private String code;
        private String name;
    }

    @Data
    @Builder
    public static class Country {
        private String code;
        private String name;
    }
}
