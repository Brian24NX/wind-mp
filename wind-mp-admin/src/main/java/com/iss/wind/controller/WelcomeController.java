package com.iss.wind.controller;

import static com.hanson.rest.enmus.ErrorCodeEnum.INVALID_PARAMETER;

import com.hanson.rest.SimpleResult;
import com.iss.wind.client.RoutingFinderClient;
import com.iss.wind.client.WindAuthClient;
import com.iss.wind.client.dto.sechedule.RoutingFinderResp;
import com.iss.wind.common.constant.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 示例控制器
 * @author hanson
 */
@Api(value = "示例控制器")
@RestController
@Slf4j
public class WelcomeController {
	@Autowired
	private RoutingFinderClient routingFinderClient;

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


	@GetMapping("/routing-finder")
	public SimpleResult<List<RoutingFinderResp>> routings(String placeOfLoading,String placeOfDischarge) {
		List<RoutingFinderResp> routing = routingFinderClient.routings(placeOfLoading, placeOfDischarge,null,null,null,null,null);
		return SimpleResult.success(routing);
	}
}
