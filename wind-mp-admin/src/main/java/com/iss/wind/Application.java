package com.iss.wind;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Hanson
 * @date 2021/11/18  17:49
 */
@SpringBootApplication
@MapperScan(basePackages = "com.iss.wind.*")
@ComponentScan(basePackages = "com.iss.wind.*")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
