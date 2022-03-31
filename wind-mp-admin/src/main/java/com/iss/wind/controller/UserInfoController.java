package com.iss.wind.controller;

/**
 * @author Hanson
 * @date 2021/11/22  22:09
 */

import com.hanson.mybatis.domain.PageQuery;
import com.hanson.rest.PageResult;
import com.hanson.rest.SimpleResult;
import com.iss.wind.common.bo.UserInfoBo;
import com.iss.wind.common.constant.Constant;
import com.iss.wind.serevice.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 示例控制器
 * @author hanson
 */
@Api(value = "示例控制器")
@RestController
public class UserInfoController {
	@Autowired
	private UserInfoService userInfoService;

	@GetMapping("/user-info/detail/{id}")
	@ApiImplicitParams({@ApiImplicitParam(name = Constant.TOKEN, paramType = "header" ,required = false)})
	public SimpleResult<UserInfoBo> get(@PathVariable("id") Long id) {
		UserInfoBo userInfoBo = userInfoService.get(id);
		return SimpleResult.success(userInfoBo);
	}

	@PostMapping("/user-info/list")
	public SimpleResult<List<UserInfoBo>> list(@RequestBody UserInfoBo userInfoBo) {
		List<UserInfoBo> ret = userInfoService.list(userInfoBo);
		return SimpleResult.success(ret);
	}

	@PostMapping("/user-info/page")
	public SimpleResult<PageResult<UserInfoBo>> page(@RequestBody PageQuery<UserInfoBo> pageQuery) {
		PageResult<UserInfoBo> page = userInfoService.page(pageQuery);
		return SimpleResult.success(page);
	}


	/**
	 * 枚举传入 name
	 * 如果传入主键则是更新，否则是新增。
	 */
	@PostMapping("/user-info/save")
	public SimpleResult<UserInfoBo> save(@RequestBody UserInfoBo userInfoBo) {
		UserInfoBo result = userInfoService.save(userInfoBo);
		return SimpleResult.success(result);
	}

	@DeleteMapping("/user-info/del/{id}")
	public SimpleResult<Integer> del(@PathVariable("id") Long id) {
		return SimpleResult.success(userInfoService.del(id));
	}
}
