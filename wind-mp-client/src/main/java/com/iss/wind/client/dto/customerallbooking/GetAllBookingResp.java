package com.iss.wind.client.dto.customerallbooking;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class GetAllBookingResp {
    private String bookingReference;
    private PortOfLoading portOfLoading;
    private PortOfDischarge portOfDischarge;
    private PlaceOfReceipt placeOfReceipt;
    private PlaceOfDelivery placeOfDelivery;
    private NamedAccount namedAccount;
    private BookingParty bookingParty;
    private Shipper shipper;
    private Consignee consignee;
    private DecidingParty decidingParty;
    private Forwarder forwarder;
    private ExportTransportation exportTransportation;
    private Integer containerQuantity;
    private Boolean deleted;
    private CreatedDate createdDate;

    @Data
    @Builder
    private static class PortOfLoading {
        private String code;
        private String name;
        private String countryCode;
        private String countryName;
        private String placeType;
    }

    @Data
    @Builder
    private static class PortOfDischarge {
        private String code;
        private String name;
        private String countryCode;
        private String countryName;
        private String placeType;
    }

    @Data
    @Builder
    private static class PlaceOfReceipt {
        private String code;
        private String name;
        private String countryCode;
        private String countryName;
        private String placeType;
    }

    @Data
    @Builder
    private static class PlaceOfDelivery {
        private String code;
        private String name;
        private String countryCode;
        private String countryName;
        private String placeType;
    }

    @Data
    @Builder
    private static class NamedAccount {
        private String code;
        private String text;
    }

    @Data
    @Builder
    private static class BookingParty {
        private String code;
        private String text;
    }

    @Data
    @Builder
    private static class Shipper {
        private String code;
        private String text;
    }

    @Data
    @Builder
    private static class Consignee {
        private String code;
        private String text;
    }

    @Data
    @Builder
    private static class DecidingParty {
        private String code;
        private String text;
    }

    @Data
    @Builder
    private static class Forwarder {
        private String code;
        private String text;
    }

    @Data
    @Builder
    private static class ExportTransportation {
        private String voyageReference;
        private String localVoyageReference;
        private String vesselCode;
        private String vesselName;
        private EstimatedDateOfArrival estimatedDateOfArrival;
        private EstimatedDateOfDeparture estimatedDateOfDeparture;
    }

    @Data
    @Builder
    private static class CreatedDate {
        private Date local;
        private Date utc;
    }

    @Data
    @Builder
    private static class EstimatedDateOfArrival {
        private Date local;
        private Date utc;
    }

    @Data
    @Builder
    private static class EstimatedDateOfDeparture {
        private Date local;
        private Date utc;
    }
}
