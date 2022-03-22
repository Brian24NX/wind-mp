package com.iss.wind.client.dto.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Data;

/**
 * @author Hanson
 * @date 2021/8/3  10:36
 */
@Data
public class WindAccessTokenResp {
	private String access_token;
	private String token_type;
	private String expires_in;
}
