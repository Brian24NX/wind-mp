DROP TABLE IF EXISTS `wx_user_info`;
CREATE TABLE `wx_user_info` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `user_code` varchar(20) NOT NULL DEFAULT '' COMMENT '用户编号',
    `name` varchar(20) NOT NULL COMMENT '用户姓名',
    `nick_name` varchar(20) NOT NULL COMMENT '用户昵称',
    `open_id` varchar(20) NOT NULL COMMENT '微信openId',
    `union_id` varchar(20) NOT NULL COMMENT '微信unionId',
    `channel` tinyint(4) NOT NULL COMMENT '注册渠道 1:公众号 2:小程序',
    `mobile` varchar(64) NOT NULL COMMENT '手机号',
    `mobile_md5` varchar(32) NOT NULL COMMENT '手机号md5',
    `deleted` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否删除 0-删除 1-正常',
    `create_user` bigint(20)  COMMENT '创建人user_id',
    `update_user` bigint(20)  COMMENT '更新人user_id',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_user_code` (user_code),
    INDEX `idx_open_id` (open_id),
    INDEX `idx_union_id` (union_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信用户表';


DROP TABLE IF EXISTS `hip_remote_call_record`;
CREATE TABLE `hip_remote_call_record` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `module` int(4) NULL COMMENT '访问模块 10:SSO,20:航线,30:货物,40:价格',
    `url` varchar(20) NOT NULL DEFAULT '' COMMENT '访问地址',
    `req` text NOT NULL COMMENT '请求参数',
    `resp` text NOT NULL COMMENT '接口回执',
    `http_status` int(4) NOT NULL COMMENT '接口状态',
    `deleted` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否删除 0-删除 1-正常',
    `create_user` bigint(20)  COMMENT '创建人user_id',
    `update_user` bigint(20)  COMMENT '更新人user_id',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='hip_api调用记录表';