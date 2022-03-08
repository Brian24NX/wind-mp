package com.iss.wind.client.dto.sechedule;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Hanson
 * @date 2022/3/3  16:31
 */
@Data
@Builder
public class RoutingFinderReq {

    /**
     * Place of loading point code, from CMA CGM location Referential - referential.location e.g. CNSHA
     */
    private String placeOfLoading;
    /**
     * Place of discharge point code, from CMA CGM location Referential - referential.location e.g. NLRTM
     */
    private String placeOfDischarge;

    private String shippingCompany;
    private String departureDate;
    private String arrivalDate;
    private Integer searchRange;
    private String polVesselIMO;
    private String polServiceCode;
    private String tsPortCode;
    private Integer maxTs;
    private List<String> specificRoutings;
    private Boolean useRoutingStatistics;
    private String range;
}
