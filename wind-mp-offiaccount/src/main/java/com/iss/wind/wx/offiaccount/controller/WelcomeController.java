package com.iss.wind.wx.offiaccount.controller;

import static com.hanson.rest.enmus.ErrorCodeEnum.INVALID_PARAMETER;

import com.hanson.rest.SimpleResult;
import com.iss.wind.common.constant.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 示例控制器
 * @author hanson
 */
@Api(value = "示例控制器")
@RestController
public class WelcomeController {
	@GetMapping("/api/v1/hello")
	@ApiOperation("hello world")
	public SimpleResult<String> hello() {
		return SimpleResult.success("hello world");
	}

	@GetMapping("/api/v1/check-token")
	@ApiImplicitParams({@ApiImplicitParam(name = Constant.TOKEN, paramType = "header" ,required = false)})
	public SimpleResult<String> checkHeader(HttpServletRequest request) {
		String header = request.getHeader(Constant.TOKEN);
		if(!"hanson-token".equals(header)){
			return SimpleResult.fail(INVALID_PARAMETER);
		}
		return SimpleResult.success(String.format("header :%s",request.getHeader(Constant.TOKEN)));
	}
}
