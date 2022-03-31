package com.iss.wind;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Hanson
 * @date 2021/11/18  17:49
 */
@SpringBootApplication
@MapperScan(basePackages = "com.iss.wind.*")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
