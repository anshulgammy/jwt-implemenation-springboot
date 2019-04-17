package com.techbrunch.jwt.authorizationserver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "authorization")
public class ApplicationConfigurationProp {
	private String username;
	private String password;
	private String secretkey;
	private String expirationtime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecretkey() {
		return secretkey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}

	public String getExpirationtime() {
		return expirationtime;
	}

	public void setExpirationtime(String expirationtime) {
		this.expirationtime = expirationtime;
	}

}
