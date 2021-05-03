package com.ggbs.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	@GetMapping("/")
	public String healthcheck() {
		return "Security app is up";
	}

	@GetMapping("/admin")
	public String admin() {
		return "Welcome Admin";
	}

	@GetMapping("/user")
	public String user() {
		return "Welcome User";
	}
}
