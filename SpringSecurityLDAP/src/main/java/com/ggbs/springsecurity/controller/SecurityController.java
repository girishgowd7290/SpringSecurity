package com.ggbs.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	@GetMapping("/ldap")
	public String ldapSecurity() {
		return "Spring Security with LDAP";
	}
}
