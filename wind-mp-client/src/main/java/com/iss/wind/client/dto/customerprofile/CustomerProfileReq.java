package com.iss.wind.client.dto.customerprofile;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerProfileReq {

    private String ccgId;
    private String shippingCompany;

}
