package com.iss.wind.client.dto.customerbooking;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerBookingReq {
    private String ccgId;
    private String shippingCompany;
    private String range;
}
