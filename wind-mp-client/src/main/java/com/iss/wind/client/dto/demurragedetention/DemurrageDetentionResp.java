package com.iss.wind.client.dto.demurragedetention;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DemurrageDetentionResp {

    private String direction;
    private Tariff tariff;
    private List<DetentionDemurrageEquipment> detentionDemurrageEquipments;
    private DemDetConditions demDetConditions;

    @Data
    @Builder
    public static class Tariff{
        private String tariffCode;
        private String tariffName;
        private String tariffOriginCode;
        private String tariffOriginName;
        private String validityFrom;
        private String validityTo;
    }

    @Data
    @Builder
    public static class DetentionDemurrageEquipment{
        private String equipmentSizeType;
        private FreeDays freeDays;
    }

    @Data
    @Builder
    public static class FreeDays{
        private Integer noOfFreeDays;
        private String freeDaysType;
    }

    @Data
    @Builder
    public static class DemDetConditions{
        private String equipmentSizeType;
        private String freeDaysType;
        private String moveType;
        private String tariffType;
        private Integer freeDays;
    }

}
