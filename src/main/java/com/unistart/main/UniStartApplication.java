package com.unistart.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.unistart.config.JPAConfiguration;

@Import(JPAConfiguration.class)
@SpringBootApplication(scanBasePackages = {"com.unistart"})
public class UniStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniStartApplication.class, args);
	}
}
