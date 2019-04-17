package com.techbrunch.jwt.authorizationserver.model;

import org.springframework.stereotype.Component;

@Component
public class ValidateTokenResponse {
	private String tokenString;
	private Boolean tokenStatus;

	public String getTokenString() {
		return tokenString;
	}

	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}

	public Boolean getTokenStatus() {
		return tokenStatus;
	}

	public void setTokenStatus(Boolean tokenStatus) {
		this.tokenStatus = tokenStatus;
	}

	public ValidateTokenResponse() {

	}

}
