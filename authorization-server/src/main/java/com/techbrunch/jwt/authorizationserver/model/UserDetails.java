package com.techbrunch.jwt.authorizationserver.model;

import org.springframework.stereotype.Component;

@Component
public class UserDetails {
	private String username;
	private String password;
	private String jwtToken;
	private Boolean isJwtTokenValid;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserDetails() {

	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsJwtTokenValid() {
		return isJwtTokenValid;
	}

	public void setIsJwtTokenValid(Boolean isJwtTokenValid) {
		this.isJwtTokenValid = isJwtTokenValid;
	}

}
