package com.iss.wind.common.util.logutil;

import java.lang.annotation.*;

/**
 * @Description: 自定义日志注解
 */
//什么时候使用该注解，我们定义为运行时
@Retention(RetentionPolicy.RUNTIME)
//注解用于什么地方，我们定义为作用于方法上
@Target({ElementType.METHOD})
//注解是否将包含在 JavaDoc 中
@Documented
public @interface WebLog {

    /**
     * 日志描述信息
     *
     * @return
     */
    String description() default "";
}