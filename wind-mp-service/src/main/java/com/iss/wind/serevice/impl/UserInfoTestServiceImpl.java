package com.iss.wind.serevice.impl;

import com.hanson.mybatis.domain.PageQuery;
import com.hanson.rest.PageResult;
import com.iss.wind.common.bo.UserInfoBo;
import com.iss.wind.dao.mappers.UserInfoMapper;
import com.iss.wind.dao.mappers.UserInfoTestMapper;
import com.iss.wind.serevice.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserInfoTestServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoTestMapper userInfoMapper;

    @Override
    public UserInfoBo get(Long id) {
        return userInfoMapper.get(id);
    }

    @Override
    public UserInfoBo save(UserInfoBo userInfoBo) {
        return null;
    }

    @Override
    public Integer del(Long id) {
        return null;
    }


    @Override
    public List<UserInfoBo> list(UserInfoBo userInfoBo) {
        return null;
    }

    @Override
    public PageResult<UserInfoBo> page(PageQuery<UserInfoBo> pageQuery) {
        return null;
    }

}
