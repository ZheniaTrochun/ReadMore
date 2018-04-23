package com.yevhenii.kpi.readmore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableWebMvc
public class ReadMoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadMoreApplication.class, args);
	}
}
