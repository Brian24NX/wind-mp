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
public class VerifyTokenResp {
	private String token;
	@JsonProperty("user_info")
	private UserInfoResp userInfo;
	@JsonProperty("token_info")
	private TokenInfoResp tokenInfo;

	@Data
	public static class UserInfoResp{
		private long id;
		@JsonProperty("usercode")
		private String userCode;
		private String name;
		private String avatar;
		private String account;
		private String mobile;
		private String homePhone;
		private String compPhone;
		private String orgCode;
		private String orgName;
		private String positionCode;
		private String positionName;
		private String superior;
		private String officeAddress;
		private String officeAddressName;
		private String level;
		private String zhiwuSeriel;
		private long entryDate;
		private String positionStatus;
		private String userRecord;
		private String lastLeaveDate;
		private int status;
		private String email;
		private String sex;
		private String avatarNeedAddedResolution;
		private String workerDuty;
		private String displayName;
		private String positionType;
		private String companyCode;
		private String agentTitle;
		private String shopCode;
		private String fax;
	}

	@Data
	public static class TokenInfoResp{
		private long id;
		private String token;
		private String source;
		private long ucid;
		private String ip;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date ctime;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date mtime;
		private String user_agent;
		private String device_id;
		private String extension_info;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date last_access_time;
	}
}
