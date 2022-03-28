package com.iss.wind.client.dto.submitquotations;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SubmitQuotationsResp {
    /*
     * 文档结果返回，不太清晰，待确认后再完善
     * responses:
        201:
          description: SQ created successfully
          content:
            application/json:
              schema:
                type: string
            text/json:
              schema:
                type: string
     *  https://gitlab.cma-cgm.com/SOA/Cartography/-/blob/master/Swagger/Implementation/commercial/Service_SWAGGER_Commercial_Pricing_v2.yaml
     */
}
