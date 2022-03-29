package com.iss.wind.client.dto.shipmenttracking;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ShipmentTrackingResp {
    private PortOfLoading portOfLoading;
    private String portOfLoadingCountryCode;
    private PortOfDischarge portOfDischarge;
    private String portOfDischargeCountryCode;
    private String voyageReference;
    private Integer nbUnits;
    private List<Route> routes;

    @Data
    @Builder
    private static class PortOfLoading {
        private String code;
        private String name;
        private String unLocode;
    }

    @Data
    @Builder
    private static class PortOfDischarge {
        private String code;
        private String name;
        private String unLocode;
    }

    @Data
    @Builder
    private static class Route {
        private List<JourneyLeg> journeyLegs;
        private List<Container> containers;
    }

    @Data
    @Builder
    private static class JourneyLeg {
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
    private static class Container {
        private String id;
        private Integer size;
        private String emptyReturnDepot;
        private String type;
        private List<Movement> movements;
    }

    @Data
    @Builder
    private static class PointFrom {
        private String code;
        private String name;
        private String unLocode;
    }

    @Data
    @Builder
    private static class VesselFrom {
        private String code;
        private String name;
        private String imo;
    }

    @Data
    @Builder
    private static class VesselTo {
        private String code;
        private String name;
        private String imo;
    }

    @Data
    @Builder
    private static class PointTo {
        private String code;
        private String name;
        private String unLocode;
    }

    @Data
    @Builder
    private static class FacilityFrom {
        private String facilityType;
        private String internalCode;
        private List<FacilityCodification> facilityCodifications;
        private String name;
    }

    @Data
    @Builder
    private static class FacilityTo {
        private String facilityType;
        private String internalCode;
        private List<FacilityCodification> facilityCodifications;
        private String name;
    }

    @Data
    @Builder
    private static class Movement {
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
    private static class FacilityCodification {
        private String codificationType;
        private String codification;
    }

    @Data
    @Builder
    private static class Status {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class Facility {
        private String facilityType;
        private String internalCode;
        private List<FacilityCodification> facilityCodifications;
        private String name;
    }

    @Data
    @Builder
    private static class PointLocation {
        private String code;
        private String name;
        private String unLocode;
    }

    @Data
    @Builder
    private static class Vessel {
        private String code;
        private String name;
        private String imo;
    }


}
