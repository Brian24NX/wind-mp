package com.iss.wind.client.dto.auth;

import lombok.Builder;
import lombok.Data;

/**
 * @author Hanson
 * @date 2021/8/3  10:36
 */
@Data
@Builder
public class VerifyTokenReq {
	private String source;
	private String signature;
	private String token;
}
