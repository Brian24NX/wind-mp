package com.iss.wind.client.dto.demurragedetention;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DemurrageDetentionReq {

    private Parameter parameters;
    private String portOfLoading;
    private String portOfDischarge;
    private String[] directions;
    private String[] tariffCodes;
    private String shippingCompany;
    private String placeOfOrigin;
    private String finalPlaceOfDelivery;
    private String simulationDate;
    private String contractReference;
    private String usAmendmentNumber;
    private String usAppendixCode;
    private String usBulletCode;
    private String commodity;
    private String[] equipmentSizeTypes;
    private boolean refrigerated;
    private boolean hazardous;
    private boolean oversize;
    private String businessPartner;

    @Data
    @Builder
    public static class Parameter{
        private String range;
        private String ccgid;
    }
}
