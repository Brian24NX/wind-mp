package com.iss.wind.wx.offiaccount;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Hanson
 * @date 2021/11/18  17:49
 */
@SpringBootApplication
@MapperScan(basePackages = "com.iss.wind.dao.mappers.")
@ComponentScan(basePackages = "com.iss.wind.*")
public class OffiAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(OffiAccountApplication.class, args);
    }
}
