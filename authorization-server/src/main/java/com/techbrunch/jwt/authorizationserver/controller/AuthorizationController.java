package com.techbrunch.jwt.authorizationserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/token")
public class AuthorizationController {

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public void getToken() {
		System.out.println("Somebody requested for token");
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public void validateToken() {
		System.out.println("Somebody requested for validating the token");
	}

}
