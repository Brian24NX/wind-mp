package com.iss.wind.client.dto.chargefindertariff;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChargeFinderTariffReq {

    private String portOfLoading;
    private String portOfDischarge;
    private String validityDate;
    private boolean reefer;
    private boolean hazardous;
    private boolean oversized;
    private String shippingCompany;
    private String range;

}
