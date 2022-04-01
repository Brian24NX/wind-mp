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
        private String id;
        private Integer size;
        private String emptyReturnDepot;
        private String type;
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
        private String statusOrder;
        private Status status;
        private String date;
        private String reportedOn;
        private String poolLocation;
        private Facility facility;
        private PointLocation pointLocation;
        private String voyageReference;
        private Vessel vessel;
        private String pointOfDischarge;
        private String portOfOrigin;
        private String portOfLoading;
        private String finalPod;
        private String finalDest;
        private String countryCode;
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
