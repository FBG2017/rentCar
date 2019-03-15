package com.fbg.cn.rent_springboot.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置类
 * Created by thinkam on 17-12-17.
 */
@Component
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo());
	}

	/**
	 * api接口作者相关信息
	 */
	private ApiInfo apiInfo() {
		Contact contact = new Contact("thinkam", "https://github.com/codethereforam", "995635324@qq.com");
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title("物品租赁平台")
				.description("前台restful接口文档")
				.contact(contact)
				.version("2.0")
				.build();
		return apiInfo;
	}
}
