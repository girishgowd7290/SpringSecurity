package com.ggbs.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class SpringSecurityOAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityOAuthApplication.class, args);
	}
	
	@GetMapping("/")
	public String hello() {
		return "Hello";
	}
	
	

}
