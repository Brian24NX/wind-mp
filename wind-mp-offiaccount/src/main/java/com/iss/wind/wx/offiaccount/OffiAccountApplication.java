package com.iss.wind.wx.offiaccount;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Hanson
 * @date 2021/11/18  17:49
 */
//@SpringBootApplication
@EnableAutoConfiguration(exclude = { FreeMarkerAutoConfiguration.class })
@MapperScan(basePackages = "com.iss.wind.*")
@ComponentScan(basePackages = "com.iss.wind.*")
public class OffiAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(OffiAccountApplication.class, args);
    }
}
