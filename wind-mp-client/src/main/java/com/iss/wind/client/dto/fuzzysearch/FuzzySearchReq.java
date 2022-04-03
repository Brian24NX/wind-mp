package com.iss.wind.client.dto.fuzzysearch;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FuzzySearchReq {

    private String codeStarts;
    private String nameStarts;
    private String nameContains;
    private String codeOrNameContains;
    private String smartNameCodeSearch;
    private String unLoCode;
    private String countryCode;
    private String pointType;
    private boolean assignedToRoute;
    private boolean hub;
    private boolean poi;
    private boolean pop;
    //private String range;//放到 header中的

}
