package com.iss.wind.client.dto.searchquotations;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchQuotationsResp {

    private List<PartialMatche> perfectMatches;
    private List<PartialMatche> partialMatches;
    private Integer nbResults;
    private boolean hasMatches;
    private String loggedId;

    @Data
    @Builder
    public static class PartialMatche{
        private String quoteLineExternalId;
        private Integer quoteLineId;
        private String quoteLineStatus;
        private String quotationReference;
        private String quoteLineType;
        private Integer quoteLineAmendementNumber;
        private String exportMovementType;
        private String importMovementType;
        private String exportInlandPointCode;
        private String portOfLoading;
        private String portOfDischarge;
        private String origin;
        private String destination;
        private String importInlandPointCode;
        private boolean oversize;
        private boolean shipperOwnedContainer;
        private boolean hazardous;
        private boolean reefer;
        private List<Affiliate> affiliates;
        private Tariff tariff;
        private List<Equipment> equipments;
        private String shippingCompany;
        private List<Commoditie> commodities;
        private String bulletCode;
        private boolean freightOfAllKinds;
        private String movementTypeFrom;
        private String movementTypeTo;
        private String exportModeOfTranportCode;
        private String importModeOfTranportCode;
        private boolean nonOperatingReefer;
        private String validityFrom;
        private String validityTo;
        private String actualValidityFrom;
        private String actualValidityTo;
        private String additionalTariff;
        private String exportZipCode;
        private String exportPoolPointCode;
        private String exportDepotCode;
        private String exportDepotName;
        private String importZipCode;
        private String importPoolPointCode;
        private String importDepotCode;
        private String importDepotName;
        private boolean controlledAtmosphere;
        private boolean overLength;
        private boolean overWidth;
        private boolean overHeight;
        private String cargoType;
        private Integer temperature;
        private Integer ventilation;
        private String commentToCustomer;
        private String routingComment;
        private boolean exportConstruction;
        private boolean importConstruction;
        private Trade trade;
        private String groupCriteria;
        private Integer lineSequence;
        private String basePortCodeOrigin;
        private String basePortCodeDestination;
        private String paymentMethod;
        private boolean fedralMaritimeControl;
        private boolean allowSpecialQuotation;
        private Integer spotValidityInDays;
        private boolean underDeck;
        private String exportLinerTerms;
        private String importLinerTerms;
        private boolean premium;
        private String amendmentCode;
        private String segmentId;
        private String initialPortOfLoading;
        private String initalPortOfDischarge;
        private String qlKey;
        private String publicationDate;
        private String[] schedules;
        private String[] solutions;
        private String[] voyages;
        private String[] services;
        private String scheduleMatched;
        private String solutionMatched;
        private String voyageMatched;
        private String serviceMatched;
        private Integer solutionNumber;
        private Integer scheduleNumber;
        private boolean spotOffer;
        private Integer numberOfContainer;
    }

    @Data
    @Builder
    public static class Affiliate{
        private String city;
        private String code;
        private String name;
        private String affiliatesType;
        private String validityFrom;
        private String validityTo;
        private Quotation quotation;
    }

    @Data
    @Builder
    public static class Quotation{
        private String quotationType;
        private String reference;
        private Integer amendmentNumber;
    }

    @Data
    @Builder
    public static class Tariff{
        private String tarrifType;
        private String reference;
        private String basedRateType;
        private String dealPeriod;
    }

    @Data
    @Builder
    public static class Equipment{
        private String code;
        private Integer oceanFreightRate;
        private String currencyCode;
        private Integer taoRate;
        private String taoRateCurrencyCode;
        private Integer tadRate;
        private String tadRateCurrencyCode;
        private Integer netWeight;
        private Integer maxNetWeight;
        private Integer volume;
        private Integer height;
        private Integer width;
        private Integer length;
        private String sizeUnitOfMeasure;
        private String calculationType;
        private Integer tareWeight;
    }

    @Data
    @Builder
    public static class Commoditie{
        private String commodityType;
        private String code;
        private String name;
    }

    @Data
    @Builder
    public static class Trade{
        private String appendixCode;
        private String governingTariffCode;
        private String governingTariffApp;
        private boolean autoShipperOwned;
        private boolean autoHazardous;
        private boolean autoFlatRack;
        private boolean autoOpenTop;
        private boolean autoReefer;
        private boolean autoTank;
        private boolean autoOversize;
        private boolean autoGarment;
        private String name;
        private String appendixId;
    }
}
