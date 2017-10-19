package com.unistart.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.unistart.config.JPAConfiguration;
import com.unistart.constant.UrlConstant;

@Import(JPAConfiguration.class)
@EnableScheduling
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"com.unistart"})
public class UniStartApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(UniStartApplication.class, args);
	}
	
//	public void addResourceHandles(ResourceHandlerRegistry registry){
//		registry.addResourceHandler(UrlConstant.IMAGE_API).addResourceLocations(UrlConstant.UNIVERSITY_FOLDER);
//	}
}
