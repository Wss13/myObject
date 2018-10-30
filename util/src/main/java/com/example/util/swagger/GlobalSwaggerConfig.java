/*
* @(#) GlobalSwaggerConfig.java 2018/09/19
* 
* Copyright 2018 CEC(Fujian) Healthcare Big Data Operation Service Co., Ltd. All rights reserved.
*/
package com.example.util.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * <code>GlobalSwaggerConfig</code> 
 *
 * @version  v0.1 2018/09/19
 *  @author    liumingchao
 * @see      
 * @since    JDK1.8 
 */
@Configuration
public class GlobalSwaggerConfig {


	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.select()
				/*制定swagger所扫描得包*/
				.apis(RequestHandlerSelectors.basePackage("com.example")).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("app接口文档").description("api根地址：http://localhost:1111/")
				// .termsOfServiceUrl("")
				.contact(new Contact("liumc", "h", "33828053@qq.com")).version("1.0")
				.build();
	}

}
