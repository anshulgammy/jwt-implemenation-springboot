package com.techbrunch.jwt.authorizationserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techbrunch.jwt.authorizationserver.model.UserDetails;
import com.techbrunch.jwt.authorizationserver.service.AuthorizationServerService;

@RestController
@RequestMapping(value = "/token")
public class AuthorizationController {

	@Autowired
	AuthorizationServerService service;

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public ResponseEntity<UserDetails> getToken(@RequestBody final UserDetails userDetails) {
		System.out.println("Somebody requested for token");
		return service.performValidationAndReturnToken(userDetails);
	}

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public ResponseEntity<UserDetails> validateToken(@RequestBody final UserDetails userDetails) {
		System.out.println("Somebody requested for validating the token");
		return service.validateTokenAndReturnResponse(userDetails);
	}

}
