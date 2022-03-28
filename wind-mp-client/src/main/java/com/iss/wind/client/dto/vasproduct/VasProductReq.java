package com.iss.wind.client.dto.vasproduct;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VasProductReq {

    private String shippingCompany;
    private String brand;
    private String agencyNetwork;
    private String quotationReference;
    private String placeReceipt;
    private String portLoading;
    private String portDischarge;
    private String placeDelivery;
    private String placeOfPayment;
    private String importMovementType;
    private String importHaulageMode;
    private String exportMovementType;
    private String exportHaulageMode;
    private String applicationDate;
    private String locale;
    private String channel;
    private String typeOfBL;
    private List<BookingParty> bookingParties;
    private String currency;
    private List<Cargoe> cargoes;
    private String[] subscribedCharges;

    @Data
    @Builder
    public static class BookingParty{
        private String partnerCode;
        private boolean bookingParty;
        private String role;
        private String name;
    }

    @Data
    @Builder
    public static class Cargoe{
        private Integer cargoNumber;
        private String commodityName;
        private String equipmentSize;
        private String equipmentTypeDescription;
        private String commodityCode;
        private String packageCode;
        private Integer packageBookedQuantity;
        private Integer totalNetWeight;
        private String uomWeight;
        private Integer volume;
        private String uomVolume;
        private boolean hazardous;
        private boolean oversize;
        private boolean refrigerated;
        private boolean shipperOwned;
    }

}
