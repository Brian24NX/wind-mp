package com.iss.wind.common.bo;

import com.iss.wind.common.enums.GenderEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Hanson
 * @date 2021/11/22  22:21
 */
@Data
@Builder
public class UserInfoBo {
	/**
	 *
	 */
	@ApiModelProperty
	private Long id;

	/**
	 * 用户编号
	 */
	@ApiModelProperty(value="用户编号",name="userCode")
	private String userCode;

	/**
	 * 用户姓名
	 */
	private String name;

	/**
	 * 性别 1:男 2:女
	 */
	private GenderEnum gender;

	/**
	 * 手机号
	 */
	private String mobile;
}
