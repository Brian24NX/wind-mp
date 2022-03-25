package com.iss.wind.client.dto.customeruserinfo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class CustomerUserInfoResp {
    private String sub;
    private String upn;
    private String role;
    private String givenName;
    private String familyName;
    private String email;

}
