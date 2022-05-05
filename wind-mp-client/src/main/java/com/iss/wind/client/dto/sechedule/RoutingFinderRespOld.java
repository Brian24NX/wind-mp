package com.iss.wind.client.dto.sechedule;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Hanson
 * @date 2022/3/3  16:06
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoutingFinderRespOld {
    private String shippingCompany;
    private Integer solutionNo;
    private Integer transitTime;
    private String specificRoutings;
    private List<RoutingDetail> routingDetails;

    @ApiModelProperty(value = "是否直达")
    private boolean directFlag = false;;
    @ApiModelProperty(value = "是否最早到达")
    private boolean earlyFlag = false;
    @ApiModelProperty(value = "排序编号")
    private Integer order;
    //前端要的
    @ApiModelProperty(value = "起运港")
    private String pointfrom;
    @ApiModelProperty(value = "起运时间")
    private String departuredate;
    @ApiModelProperty(value = "目的港")
    private String pointto;
    @ApiModelProperty(value = "到达时间")
    private String arrivaldate;
    @ApiModelProperty(value = "转（船）运次数")
    private int transhipment;
    @ApiModelProperty(value = "第一艘船名")
    private String shipname;
    @ApiModelProperty(value = "多个服务用/隔开")
    private String service;
    @ApiModelProperty(value = "开始运输工具")
    private String startMeanOfTransport;
    @ApiModelProperty(value = "结束运输工具")
    private String endMeanOfTransport;
    //原数据就进行排序打标签
    @ApiModelProperty(value = "最早离港")
    private boolean departureDateFlag = false;
    @ApiModelProperty(value = "最早到港")
    private boolean arrivalDateFlag = false;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RoutingDetail{
        private PointFrom pointFrom;
        private PointTo pointTo;
        private Transportation transportation;
        private int legTransitTime;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CutOff{
        private PortCutoff portCutoff;
        private Vgm vgm;
        // private StandardBookingAcceptance standardBookingAcceptance;
        // private SpecialBookingAcceptance specialBookingAcceptance;
        // private ShippingInstructionAcceptance shippingInstructionAcceptance;
        private CustomsAcceptance customsAcceptance;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PortCutoff{
        private Date local;
        private Date utc;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Vgm{
        private Date local;
        private Date utc;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public  static class CustomsAcceptance {
        private Date local;
        private Date utc;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PointFrom{
        private Location location;
        private String callId;
        private String departureDateLocal;
        private String departureDateGmt;
        private String portCutoffDate;
        private String portCutoffDateGmt;
        private String vgmCutoffDate;
        private String vgmCutoffDateGmt;
        private String customsCutoffDate;
        private String customsCutoffDateGmt;
        private CutOff cutOff;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FacilityCodifications{
        private String codificationType;
        private String codification;
        private List<FacilityCodifications> facilityCodifications;
        private String name;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocationCodifications {
        private String codificationType;
        private String codification;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Facility{
        private String facilityType;
        private String internalCode;
        private List<FacilityCodifications> facilityCodifications;
        private String name;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Location{
        private String name;
        private String internalCode;
        private List<LocationCodifications> locationCodifications;
        private Facility facility;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PointTo{
        private Location location;
        private String callId;
        private String arrivalDateLocal;
        private String arrivalDateGmt;


    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Transportation{
        private String transportationPhase;
        private String meanOfTransport;
        private Vehicule vehicule;
        private Voyage voyage;


    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Voyage {
        private String voyageReference;
        private Service service;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class  Vehicule {
        private String vehiculeType;
        private String vehiculeName;
        private String reference;
        private String referenceType;
        private String internalReference;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class  Service {
        private String code;
        private String internalCode;
    }
}
