package com.iss.wind.client.dto.searchquotations;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchQuotationsReq {

    private String equipmentType;
    private String[] affiliates ;
    private String placeOfLoading;
    private String placeOfDischarge;
    private String shippingCompany;
    private String simulationDate;
    private String placeOfOrigin;
    private String finalPlaceOfDelivery;
    private String quotationReference;
    private String namedAccount;
    private String sessionId;
    private String source;
    private String sourceShipcompCode;
    private String isAffiliatesSelected;
    private String ccgId;

}
