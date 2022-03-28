package com.iss.wind.client.dto.submitquotations;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SubmitQuotationsReq {

    private Parameters parameters;
    private String quoteLineId;
    private String portOfLoading;
    private String portOfDischarge;
    private String polCountryCode;
    private String podCountryCode;
    private String initialPortOfLoading;
    private String initalPortOfDischarge;
    private Integer numberOfContainers;
    private String equipmentSizeType;
    private Integer weightPerContainer;
    private String basePortCodeOrigin;
    private String basePortCodeDestination;
    private String additionalTariff;
    private String actualValidityFrom;
    private String actualValidityTo;
    private String[] affiliates;
    private String simulationDate;
    private boolean allowSpecialQuotation;
    private Integer spotValidityInDays;
    private List<VasChargeDetail> vasChargeDetails;
    private String routingComment;
    private List<DetentionDemurrage> detentionDemurrage;
    private String voyageRef;
    private String arrivalDate;

    @Data
    @Builder
    public static class Parameters{
        private String sessionId;
        private String source;
        private String sourceShipcompCode;
        private String ccgId;
    }

    @Data
    @Builder
    public static class VasChargeDetail{
        private String chargeName;
        private String description;
        private String expectedActions;
        private Integer rateFrom;
        private String chargeCode;
        private String levelOfCharge;
        private String calculationType;
        private Integer minimumChargeableAmount;
        private Integer maximumChargeableAmount;
        private String currency;
        private List<CargoLine> cargoLines;
        private boolean hasChargeSelected;
        private String subscriptionMode;
        private Integer subscribedAmount;
    }

    @Data
    @Builder
    public static class DetentionDemurrage{
        private String equipmentSizeType;
        private String freeDaysType;
        private String moveType;
        private String tariffType;
        private Integer freeDays;
    }

    @Data
    @Builder
    public static class CargoLine{
        private Integer cargoLineNumber;
        private Integer cargoRate;
        private Integer maxNoOfContainer;
        private Integer convertedRate;
        private String equipmentSize;
    }

}
