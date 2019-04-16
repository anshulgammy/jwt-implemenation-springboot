package com.techbrunch.jwt.authorizationserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techbrunch.jwt.authorizationserver.model.UserDetails;

@Service
public class AuthorizationServerService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserDetails userDetails;

	public Boolean isUserValid(final String username, final String password) {
		if (null != username && !"".equals(username)
				&& passwordEncoder.encode(username).equals(userDetails.getUsername()) && null != password
				&& !"".equals(password) && passwordEncoder.encode(password).equals(userDetails.getPassword())) {
			return true;
		}
		return false;
	}
}
