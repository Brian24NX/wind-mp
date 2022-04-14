package com.iss.wind.dao.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.hanson.mybatis.BasePo;
import com.hanson.mybatis.handler.EncryptColumn;
import com.hanson.mybatis.handler.EncryptHandler;
import com.iss.wind.common.enums.GenderEnum;
import lombok.*;

import java.io.Serializable;

/**
 *  * wind_mp_third_invoke_record 调用第三方记录表
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("wind_mp_third_invoke_record")
public class ThirdInvokeRecordPo extends BasePo implements Serializable {
    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 请求url
     */
    @TableField(value = "url",insertStrategy = FieldStrategy.NOT_NULL)
    private String url;

    /**
     * 请求信息
     */
    @TableField("req")
    private String req;

    /**
     * 响应信息
     */
    @TableField("resp")
    private String resp;

    /**
     * 耗费时长
     */
    @TableField(value = "consume_time")
    private String consumeTime;

    /**
     * 请求返回状态
     */
    @TableField("http_status")
    private String httpStatus;

}