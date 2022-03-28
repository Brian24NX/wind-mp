package com.iss.wind.client.dto.shipmentrequestbooking;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShipmentRequestBookingResp {

    private String bookingReference;
    private String communicationChannel;

}
