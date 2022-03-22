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
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("expires_in")
	private String expiresIn;
	private long genMillisecond;
}
