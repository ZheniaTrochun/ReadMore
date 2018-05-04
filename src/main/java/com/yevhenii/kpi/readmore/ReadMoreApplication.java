package com.yevhenii.kpi.readmore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextListener;

import java.security.Principal;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class ReadMoreApplication {

	@RequestMapping("/")
	public String test() {
		return "TEST SUCCESS";
	}

	@RequestMapping("/user")
	public Principal getPrincipal(Principal principal) {
		return principal;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReadMoreApplication.class, args);
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
}