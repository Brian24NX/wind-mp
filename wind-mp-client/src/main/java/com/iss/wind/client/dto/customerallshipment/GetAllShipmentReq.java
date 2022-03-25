package com.iss.wind.client.dto.customerallshipment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllShipmentReq {
    private String ccgId;
    private String shippingCompany;
    private Boolean owned;
    private Boolean followed;
    private Boolean draft;
    private String myReference;
    private String portOfLoading;
    private String portOfDischarge;
    private String bookingLastUpdated;
    private String range;

}
