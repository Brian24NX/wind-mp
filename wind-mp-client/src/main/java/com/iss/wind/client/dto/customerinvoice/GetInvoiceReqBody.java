package com.iss.wind.client.dto.customerinvoice;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class GetInvoiceReqBody {
    private List<String> references;
    private SearchDate searchDate;
    private Date lastUserConnectionDate;
    private Boolean limitTo6Months;
    private List<String> invoiceDomains;
    private Boolean invoice;
    private List<String> invoiceStatus;
    private List<String> invoiceDisputeStatus;
    private String quotationReference;
    private List<String> invoiceRecipient;
    private List<String> payableAt;
    private String transportationPhase;
    private Routing routing;
    private List<String> paymentStatus;
    private PaymentLocation paymentLocation;
    private Boolean includeInvoiceStatistic;

    @Data
    @Builder
    private static class SearchDate {
        private String dateType;
        private Integer monthRange;
        private Date dateFrom;
        private Date dateTo;
    }

    @Data
    @Builder
    private static class Routing {
        private List<String> placeOfReceipt;
        private List<String> portOfLoading;
        private List<String> portOfDischarge;
        private List<String> placeOfDelivery;
        private String vesselImo;
        private String vesselCMACode;
        private String voyageReference;
    }

    @Data
    @Builder
    private static class PaymentLocation {
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
    private static class LocationCodification {
        private String codificationType;
        private String codification;
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
}
