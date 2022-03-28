package com.iss.wind.client.dto.vasproduct;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VasProductResp {

    private String parentProductId;
    private String productName;
    private String productDescription;
    private String productShortDescription;
    private String productMainImage;
    private String productSheet;
    private boolean bestSeller;
    private boolean featuredProduct;
    private boolean subscriptionMode;
    private boolean subscriptionAvailable;
    private boolean contactEmail;
    private boolean tnCRequired;
    private String termsandConditions;
    private String productFamily;
    private String internalDocuments;
    private String personalizedValueLabel;
    private String taxCode;
    private Integer taxRate;
    private String taxName;
    private String currency;
    private String levelOfCharge;
    private List<ChargeDetail> chargeDetails;
    private boolean productSelected;
    private boolean existingVas;

    @Data
    @Builder
    public static class ChargeDetail{
        private String chargeName;
        private String chargeDescription;
        private String subscriptionMode;
        private Integer rateFrom;
        private String chargeCode;
        private String levelOfCharge;
        private String calculationType;
        private String minimumChargeableAmount;
        private String maximumChargeableAmount;
        private String currency;
        private Integer conversionRate;
        private String expectedActions;
        private List<CargoLine> cargoLines;
    }

    @Data
    @Builder
    public static class CargoLine{
        private Integer cargoLineNumber;
        private String commodityName;
        private String equipmentSize;
        private String equipmentTypeDescription;
        private Integer cargoRate;
        private Integer maxNoOfContainer;
        private boolean outOfGauage;
        private boolean hazardous;
        private boolean shipperOwned;
        private String currency;
        private String levelOfCharge;
    }

}
