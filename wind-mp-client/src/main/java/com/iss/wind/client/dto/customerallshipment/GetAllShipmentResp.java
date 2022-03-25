package com.iss.wind.client.dto.customerallshipment;

import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class GetAllShipmentResp {
    private String bookingReference;
    private String blReference;
    private String myReference;
    private Boolean followed;
    private String shippingCompany;
    private String quotationReference;
    private Boolean dangerous;
    private Boolean hazardousSubmitted;
    private Boolean vgmSubmitted;
    private Boolean draftReviewed;
    private Boolean reuseableBkg;
    private Boolean favorite;
    private Boolean splitBooking;
    private String status;
    private String messageStatus;
    private Routing routing;
    private Transport transport;
    private List<BookingParty> bookingParties;
    private List<Commodity> commodities;
    private PaymentInformation paymentInformation;
    private GasEmissionInformation gasEmissionInformation;
    private String eBookingReference;
    private String exportMovementType;
    private String importMovementType;
    private String exportShippingType;
    private String importShippingType;
    private String importHaulageMode;
    private String exportHaulageMode;
    private String serviceContractReference;
    private String bookingType;
    private AgencyNetwork agencyNetwork;
    private FreightPayerRole freightPayerRole;
    private BookingAgent bookingAgent;
    private Service service;
    private TerminalAtPol terminalAtPol;
    private TerminalAtPod terminalAtPod;
    private PlaceOfReceipt placeOfReceipt;
    private PlaceOfDelivery placeOfDelivery;
    private DocumentationAgent documentationAgent;
    private SwitchBlPlace switchBlPlace;
    private SwitchBlIssuancePlace switchBlIssuancePlace;
    private SwitchBlIssuanceDate switchBlIssuanceDate;
    private ShippedOnBoardDate shippedOnBoardDate;
    private BookingCutoffDate bookingCutoffDate;
    private Boolean createdByMerge;
    private Boolean createdBySplit;
    private Boolean nvocc;
    private String partLoadmaster;
    private String originOfGoods;
    private String preCarriageBy;
    private String sIReference;
    private String aesNumber;
    private String usVoyDocReference;
    private BookingCreationDate bookingCreationDate;
    private String bookingCreator;
    private String polLocalVoyage;
    private String siOrigin;
    private String siCreator;


    @Data
    @Builder
    private static class Routing {
        private ExportVoyage exportVoyage;
        private ImportVoyage importVoyage;
        private PortOfLoading portOfLoading;
        private PortOfDischarge portOfDischarge;
        private From from;
        private To to;
        private FirstPortOfTranshipment firstPortOfTranshipment;
        private EstimatedDateOfArrival estimatedDateOfArrival;
        private EstimatedDateOfDeparture estimatedDateOfDeparture;
    }

    @Data
    @Builder
    private static class Transport {
        private String exportTransportMode;
        private String importTransportMode;
        private List<ExportTransportInstruction> exportTransportInstructions;
        private List<ImportTransportInstruction> importTransportInstructions;
        private List<EmptyPickUpInformation> emptyPickUpInformations;
        private List<ReturnInformation> returnInformations;
        private String googleMapUrl;
    }

    @Data
    @Builder
    private static class BookingParty {
        private String partnerCode;
        private Boolean bookingParty;
        private String role;
        private String name;
        private List<Address> addresses;
        private String partnerReference;
        private String vatNumber;
        private String extraInformation;
        private List<LegalInformation> legalInformations;
    }

    @Data
    @Builder
    private static class Commodity {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class PaymentInformation {
        private String paymentTerms;
        private PaymentLocation paymentLocation;
        private String freightPayer;
    }

    @Data
    @Builder
    private static class GasEmissionInformation {
        private Integer sulphur;
        private Integer nitorgen;
        private Integer carbonDioxide;
        private Integer particulateMatter;
    }

    @Data
    @Builder
    private static class AgencyNetwork {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class FreightPayerRole {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class BookingAgent {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class Service {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class TerminalAtPol {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class TerminalAtPod {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class PlaceOfReceipt {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class PlaceOfDelivery {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class DocumentationAgent {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class SwitchBlPlace {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class SwitchBlIssuancePlace {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class SwitchBlIssuanceDate {
        private Date local;
        private Date utc;
        private Integer offset;
    }

    @Data
    @Builder
    private static class ShippedOnBoardDate {
        private Date local;
        private Date utc;
        private Integer offset;
    }

    @Data
    @Builder
    private static class BookingCutoffDate {
        private Date local;
        private Date utc;
        private Integer offset;
    }

    @Data
    @Builder
    private static class BookingCreationDate {
        private Date local;
        private Date utc;
        private Integer offset;
    }

    @Data
    @Builder
    private static class ExportVoyage {
        private Vessel vessel;
        private String voyageReference;
    }

    @Data
    @Builder
    private static class ImportVoyage {
        private Vessel vessel;
        private String voyageReference;
    }

    @Data
    @Builder
    private static class PortOfLoading {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class PortOfDischarge {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class From {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class To {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class FirstPortOfTranshipment {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class EstimatedDateOfArrival {
        private Date local;
        private Date utc;
        private Integer offset;
    }

    @Data
    @Builder
    private static class EstimatedDateOfDeparture {
        private Date local;
        private Date utc;
        private Integer offset;
    }

    @Data
    @Builder
    private static class ExportTransportInstruction {
        private String collectionCompanyName;
        private CollectionPoint collectionPoint;
        private PickUpTime pickUpTime;
    }

    @Data
    @Builder
    private static class ImportTransportInstruction {
        private String deliveryCompanyName;
        private DeliveryPoint deliveryPoint;
        private DeliveryTime deliveryTime;
    }

    @Data
    @Builder
    private static class EmptyPickUpInformation {
        private Depot depot;
        private Address address;
    }

    @Data
    @Builder
    private static class ReturnInformation {
        private Depot depot;
        private Address address;
    }

    @Data
    @Builder
    private static class Address {
        private String address1;
        private String address2;
        private String address3;
        private String zipCode;
        private String contact;
        private String phoneNumber;
        private String stateOrProvince;
        private String city;
        private String country;
    }

    @Data
    @Builder
    private static class LegalInformation {
        private String code;
        private String description;
        private String lliType;
        private String country;
        private String number;
    }

    @Data
    @Builder
    private static class PaymentLocation {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class Vessel {
        private String code;
        private String name;
    }

    @Data
    @Builder
    private static class CollectionPoint {
        private String address1;
        private String address2;
        private String address3;
        private String zipCode;
        private String contact;
        private String phoneNumber;
        private String stateOrProvince;
        private String city;
        private String country;
    }

    @Data
    @Builder
    private static class PickUpTime {
        private Date local;
        private Date utc;
        private Integer offset;
    }

    @Data
    @Builder
    private static class DeliveryPoint {
        private String address1;
        private String address2;
        private String address3;
        private String zipCode;
        private String contact;
        private String phoneNumber;
        private String stateOrProvince;
        private String city;
        private String country;
    }

    @Data
    @Builder
    private static class DeliveryTime {
        private Date local;
        private Date utc;
        private Integer offset;
    }

    @Data
    @Builder
    private static class Depot {
        private String code;
        private String name;
    }
}
