package com.iss.wind.configuration;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Hanson
 * @date 2021/11/19  15:56
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfiguration {

	@Value("${spring.application.name:}")
	private String appName;

	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
													  .pathMapping("/")
													  .select()
													  .apis(RequestHandlerSelectors.any())
													  .paths(Predicates.not(PathSelectors.regex("/error.*")))
													  .paths(PathSelectors.regex("/.*"))
													  .build();
	}

	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
			.title(appName)
			.description(String.format("This is a restful api document of %s.",appName))
			.version("1.0")
			.build();
	}
}
