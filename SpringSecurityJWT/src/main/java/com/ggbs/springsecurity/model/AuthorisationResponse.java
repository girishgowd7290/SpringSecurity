package com.ggbs.springsecurity.model;

public class AuthorisationResponse {

	private String jwt;

	public AuthorisationResponse() {
	}

	public AuthorisationResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}
