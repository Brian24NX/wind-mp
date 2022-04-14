package com.iss.wind.dao.mappers;

import com.hanson.mybatis.MyBaseMapper;
import com.iss.wind.dao.domain.ThirdInvokeRecordPo;
import org.apache.ibatis.annotations.Mapper;

/**
* wind_mp_third_invoke_record  调用第三方记录表
*/
@Mapper
public interface ThirdInvokeRecordMapper extends MyBaseMapper<ThirdInvokeRecordPo> {

    //新增记录
    void add(ThirdInvokeRecordPo thirdInvokeRecordPo);

}