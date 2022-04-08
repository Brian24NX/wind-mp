package com.iss.wind.client.dto.sechedule;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hanson
 * @date 2022/3/3  16:06
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoutingFinderResp {
    private String shippingCompany;
    private Integer solutionNo;
    private Integer transitTime;
    private String specificRoutings;
    private List<RoutingDetail> routingDetails;

    @ApiModelProperty(value = "是否直达")
    private boolean directFlag;
    @ApiModelProperty(value = "是否最早到达")
    private boolean earlyFlag = false;
    @ApiModelProperty(value = "排序编号")
    private Integer order;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RoutingDetail{
        private PointFrom pointFrom;
        private PointTo pointTo;
        private Transportation transportation;
        private int legTransitTime;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CutOff{
        private PortCutoff portCutoff;
        private Vgm vgm;
        // private StandardBookingAcceptance standardBookingAcceptance;
        // private SpecialBookingAcceptance specialBookingAcceptance;
        // private ShippingInstructionAcceptance shippingInstructionAcceptance;
        private CustomsAcceptance customsAcceptance;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PortCutoff{
        private Date local;
        private Date utc;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Vgm{
        private Date local;
        private Date utc;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public  static class CustomsAcceptance {
        private Date local;
        private Date utc;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PointFrom{
        private Location location;
        private String callId;
        private Date departureDateLocal;
        private Date departureDateGmt;
        private String portCutoffDate;
        private String portCutoffDateGmt;
        private String vgmCutoffDate;
        private String vgmCutoffDateGmt;
        private String customsCutoffDate;
        private String customsCutoffDateGmt;
        private CutOff cutOff;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FacilityCodifications{
        private String codificationType;
        private String codification;
        private List<FacilityCodifications> facilityCodifications;
        private String name;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocationCodifications {
        private String codificationType;
        private String codification;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Facility{
        private String facilityType;
        private String internalCode;
        private List<FacilityCodifications> facilityCodifications;
        private String name;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Location{
        private String name;
        private String internalCode;
        private List<LocationCodifications> locationCodifications;
        private Facility facility;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PointTo{
        private Location location;
        private String callId;
        private Date arrivalDateLocal;
        private Date arrivalDateGmt;


    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Transportation{
        private String transportationPhase;
        private String meanOfTransport;
        private Vehicule vehicule;
        private Voyage voyage;


    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Voyage {
        private String voyageReference;
        private Service service;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class  Vehicule {
        private String vehiculeType;
        private String vehiculeName;
        private String reference;
        private String referenceType;
        private String internalReference;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class  Service {
        private String code;
        private String internalCode;
    }
}
