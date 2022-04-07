package com.iss.wind.serevice.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public boolean setValue(String key,Object value){
        setValue(key, value, 10, TimeUnit.MINUTES);
        return true;
    }

    public boolean setValue(String key,Object value, long timeout, TimeUnit unit){
        redisTemplate.opsForValue().set(key, value, timeout, unit);
        return true;
    }

    public Object getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

}
