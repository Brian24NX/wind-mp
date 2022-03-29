package com.iss.wind.client.dto.customerallbooking;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllBookingReq {
    private String ccgId;
    private String shippingCompany;
    private String range;
}
