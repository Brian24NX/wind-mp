package com.iss.wind.client.dto.chargefindertariff;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChargeFinderTariffResp {

    private String tariffDesc;
    private Integer tariffId;
    private List<TarrifInformation> tarrifInformation;
    private boolean reefer;
    private boolean hazardous;
    private boolean oversized;

    @Data
    @Builder
    public static class TarrifInformation{
        private String chargeClass;
        private String chargeCode;
        private String chargeDescription;
        private String chargeCalculationLevel;
        private String comment;
        private String additionalComment;
        private List<PackageTariff> packageTariffs;
    }

    @Data
    @Builder
    public static class PackageTariff{
        private PackageType packageType;
        private String paymentMode;
        private AllInclusiveRate allInclusiveRate;
        private FixedCharge fixedCharge;
        private MaxWeight maxWeight;
    }

    @Data
    @Builder
    public static class PackageType{
        private String code;
        private String name;
    }

    @Data
    @Builder
    public static class AllInclusiveRate{
        private Integer amount;
        private String currencyCode;
    }

    @Data
    @Builder
    public static class FixedCharge{
        private Integer amount;
        private String currencyCode;
    }

    @Data
    @Builder
    public static class MaxWeight{
        private Integer value;
        private Uom uom;
    }

    @Data
    @Builder
    public static class Uom{
        private String code;
        private String name;
    }

}
