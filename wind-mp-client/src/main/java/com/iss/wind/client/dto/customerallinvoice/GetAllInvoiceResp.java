package com.iss.wind.client.dto.customerallinvoice;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class GetAllInvoiceResp {
    private List<Invoice> invoices;
    private List<InvoiceStatistic> invoiceStatistics;

    @Data
    @Builder
    private static class Invoice {
        private References references;
        private Routing routing;
        private Boolean invoice;
        private String invoiceDomain;
        private Integer totalAmount;
        private String currencyCode;
        private DueDate dueDate;
        private InvoiceDate invoiceDate;
        private PayableAt payableAt;
        private InvoiceRecipient invoiceRecipient;
        private InvoicePayer invoicePayer;
        private String invoiceStatus;
        private String transportationPhase;
        private String paymentStatus;
        private Boolean payment;
        private Boolean epaymentAvailable;
        private String invoicingDepartement;
        private String invoicingDeptCountry;
        private String payableBankAccountNumber;
        private String invoicePdfType;
        private String electronicInvoiceSendingMode;
        private String invoiceDocumentPaperless;
        private String invoiceDocumentTypeAuthorizedbyAgency;
        private Boolean invoiceDisputeEligibility;
        private InvoiceDispute invoiceDispute;
    }

    @Data
    @Builder
    private static class InvoiceStatistic {
        private String status;
        private Integer count;
        private List<Amount> amounts;
    }

    @Data
    @Builder
    private static class References {
        private String invoiceReference;
        private String shipmentReference;
        private String blReference;
        private String customerReference;
        private String quotationReference;
    }

    @Data
    @Builder
    private static class Routing {
        private PortOfLoading portOfLoading;
        private PortOfDischarge portOfDischarge;
        private PlaceOfReceipt placeOfReceipt;
        private PlaceOfDelivery placeOfDelivery;
        private Vessel vessel;
        private String voyageReference;
    }

    @Data
    @Builder
    private static class DueDate {
        private Date local;
        private Date utc;
    }

    @Data
    @Builder
    private static class InvoiceDate {
        private Date local;
        private Date utc;
    }

    @Data
    @Builder
    private static class PayableAt {
        private String name;
        private String locationType;
        private String internalCode;
        private String nearestUnLocode;
        private String timeZone;
        private List<LocationCodification> locationCodifications;
        private Facility facility;
    }

    @Data
    @Builder
    private static class InvoiceRecipient {
        private String code;
        private String status;
        private String shortName;
        private String fullName;
        private String countryCode;
    }

    @Data
    @Builder
    private static class InvoicePayer {
        private String code;
        private String status;
        private String shortName;
        private String fullName;
        private String countryCode;
    }

    @Data
    @Builder
    private static class InvoiceDispute {
        private String pageId;
        private String disputeProcess;
        private Date disputeClosureDate;
        private String disputeClosureReason;
        private Date disputeCreateDate;
        private String disputeOrigin;
        private String disputeRaisedBy;
        private String disputeRef;
        private String disputeStatus;
        private String disputeType;
        private String invoiceRef;
        private String invoicePartnerRef;
        private String shippingCompany;
    }

    @Data
    @Builder
    private static class Amount {
        private String currencyCode;
        private String amount;
    }

    @Data
    @Builder
    private static class PortOfLoading {
        private String name;
        private String locationType;
        private String internalCode;
        private String nearestUnLocode;
        private String timeZone;
        private List<LocationCodification> locationCodifications;
        private Facility facility;
    }

    @Data
    @Builder
    private static class PortOfDischarge {
        private String name;
        private String locationType;
        private String internalCode;
        private String nearestUnLocode;
        private String timeZone;
        private List<LocationCodification> locationCodifications;
        private Facility facility;
    }

    @Data
    @Builder
    private static class PlaceOfReceipt {
        private String name;
        private String locationType;
        private String internalCode;
        private String nearestUnLocode;
        private String timeZone;
        private List<LocationCodification> locationCodifications;
        private Facility facility;
    }

    @Data
    @Builder
    private static class PlaceOfDelivery {
        private String name;
        private String locationType;
        private String internalCode;
        private String nearestUnLocode;
        private String timeZone;
        private List<LocationCodification> locationCodifications;
        private Facility facility;
    }

    @Data
    @Builder
    private static class Vessel {
        private String imo;
        private String name;
        private String vesselCode;
        private String vesselFlag;
        private String callSign;
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
    private static class FacilityCodification {
        private String codificationType;
        private String codification;
    }

    @Data
    @Builder
    private static class LocationCodification {
        private String codificationType;
        private String codification;
    }
}
