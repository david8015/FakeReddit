package com.ga.entity;

public class JwtResponse {

	private String token;

	public JwtResponse(String jwt) {
		this.token = jwt;
	}

	public String getToken() {
		return this.token;
	}

}
