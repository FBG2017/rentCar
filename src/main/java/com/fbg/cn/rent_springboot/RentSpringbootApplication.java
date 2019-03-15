package com.fbg.cn.rent_springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.fbg.cn.rent_springboot.dao.mapper")
//@ComponentScan(basePackages = {"com.fbg.cn.rent_springboot"})
public class RentSpringbootApplication{


	public static void main(String[] args) {
		SpringApplication.run(RentSpringbootApplication.class, args);
	}

}
