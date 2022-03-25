package com.iss.wind.client.dto.shipmenttracking;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShipmentTrackingReq {
    private String shipmentRef;
    private String eqpid;
}
