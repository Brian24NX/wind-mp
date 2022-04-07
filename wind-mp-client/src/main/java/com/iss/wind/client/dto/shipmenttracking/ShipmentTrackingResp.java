package com.iss.wind.client.dto.shipmenttracking;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
@ApiModel("货物追踪响应类")
public class ShipmentTrackingResp {

    @ApiModelProperty(value = "起运港")
    private PortOfLoading portOfLoading;
    private String portOfLoadingCountryCode;
    @ApiModelProperty(value = "卸货港")
    private PortOfDischarge portOfDischarge;
    private String portOfDischargeCountryCode;
    @ApiModelProperty(value = "航次")
    private String voyageReference;
    private Integer nbUnits;
    private List<Route> routes;

    @Data
    @Builder
    public static class PortOfLoading {
        private String code;
        private String name;
        private String unLocode;
    }

    @Data
    @Builder
    public static class PortOfDischarge {
        private String code;
        private String name;
        private String unLocode;
    }

    @Data
    @Builder
    public static class Route {
        private List<JourneyLeg> journeyLegs;
        @ApiModelProperty(value = "集装箱")
        private List<Container> containers;
    }

    @Data
    @Builder
    public static class JourneyLeg {
        private Integer sequenceNumber;
        private PointFrom pointFrom;
        private VesselFrom vesselFrom;
        private VesselTo vesselTo;
        private PointTo pointTo;
        private String poolLocationFromCode;
        private FacilityFrom facilityFrom;
        private String poolLocationToCode;
        private FacilityTo facilityTo;
        private String collectionDate;
        private String voyageReference;
        private String dischargeVoyageReference;
        private String deliveryDate;
        private String shipCompCode;
    }

    @Data
    @Builder
    public static class Container {
        @ApiModelProperty(value = "集装箱号")
        private String id;
        @ApiModelProperty(value = "集装箱大小")
        private Integer size;
        private String emptyReturnDepot;
        @ApiModelProperty(value = "集装箱类型")
        private String type;
        @ApiModelProperty(value = "运输")
        private List<Movement> movements;
    }

    @Data
    @Builder
    public static class PointFrom {
        private String code;
        private String name;
        private String unLocode;
    }

    @Data
    @Builder
    public static class VesselFrom {
        private String code;
        private String name;
        private String imo;
    }

    @Data
    @Builder
    public static class VesselTo {
        private String code;
        private String name;
        private String imo;
    }

    @Data
    @Builder
    public static class PointTo {
        private String code;
        private String name;
        private String unLocode;
    }

    @Data
    @Builder
    public static class FacilityFrom {
        private String facilityType;
        private String internalCode;
        private List<FacilityCodification> facilityCodifications;
        private String name;
    }

    @Data
    @Builder
    public static class FacilityTo {
        private String facilityType;
        private String internalCode;
        private List<FacilityCodification> facilityCodifications;
        private String name;
    }

    @Data
    @Builder
    public static class Movement {
        @ApiModelProperty(value = "集装箱状态序号")
        private String statusOrder;
        @ApiModelProperty(value = "集装箱状态")
        private Status status;
        @ApiModelProperty(value = "运输日期")
        private String date;
        private String reportedOn;
        private String poolLocation;
        private Facility facility;
        @ApiModelProperty(value = "运输目的地")
        private PointLocation pointLocation;
        @ApiModelProperty(value = "航次")
        private String voyageReference;
        @ApiModelProperty(value = "航船")
        private Vessel vessel;
        @ApiModelProperty(value = "卸货港")
        private String pointOfDischarge;
        @ApiModelProperty(value = "原始地")
        private String portOfOrigin;
        @ApiModelProperty(value = "起运港")
        private String portOfLoading;
        private String finalPod;
        private String finalDest;
        private String countryCode;
        @ApiModelProperty(value = "公司编码")
        private String shipCompCode;
        private String voyageShipCompCode;
    }

    @Data
    @Builder
    public static class FacilityCodification {
        private String codificationType;
        private String codification;
    }

    @Data
    @Builder
    public static class Status {
        private String code;
        private String name;
    }

    @Data
    @Builder
    public static class Facility {
        private String facilityType;
        private String internalCode;
        private List<FacilityCodification> facilityCodifications;
        private String name;
    }

    @Data
    @Builder
    public static class PointLocation {
        private String code;
        private String name;
        private String unLocode;
    }

    @Data
    @Builder
    public static class Vessel {
        private String code;
        private String name;
        private String imo;
    }


}
