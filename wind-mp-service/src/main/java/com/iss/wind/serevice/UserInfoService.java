package com.iss.wind.serevice;

import com.hanson.mybatis.domain.PageQuery;
import com.hanson.rest.PageResult;
import com.iss.wind.common.bo.UserInfoBo;
import java.util.List;

/**
 * @author Hanson
 * @date 2021/11/22  22:12
 */
public interface UserInfoService {
	UserInfoBo save(UserInfoBo userInfoBo);
	Integer del(Long id);
	UserInfoBo get(Long id);
	List<UserInfoBo> list(UserInfoBo userInfoBo);
	PageResult<UserInfoBo> page(PageQuery<UserInfoBo> pageQuery);
}
