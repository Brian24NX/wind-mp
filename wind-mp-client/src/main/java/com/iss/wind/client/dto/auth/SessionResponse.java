package com.iss.wind.client.dto.auth;

import lombok.Data;

/**
 * @author Hanson
 * @date 2021/8/3  10:27
 */
@Data
public class SessionResponse<T> {
	private Integer error_code;
	private String error;
	private T data;
}
