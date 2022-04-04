package com.iss.wind.dao.mappers;

import com.hanson.mybatis.MyBaseMapper;
import com.iss.wind.common.bo.UserInfoBo;
import org.apache.ibatis.annotations.Mapper;

/**
tmp_user_info  用户示例表
* @author: huhanlin 2021-12-03 16:31:42
*/
@Mapper
public interface UserInfoTestMapper extends MyBaseMapper<UserInfoBo> {

    UserInfoBo get(Long id);

}