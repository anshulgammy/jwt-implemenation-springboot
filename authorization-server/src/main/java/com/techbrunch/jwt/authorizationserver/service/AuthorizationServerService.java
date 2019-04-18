package com.techbrunch.jwt.authorizationserver.service;

import java.io.IOException;
import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techbrunch.jwt.authorizationserver.ApplicationConfigurationProp;
import com.techbrunch.jwt.authorizationserver.model.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthorizationServerService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserDetails userDetails;

	@Autowired
	ApplicationConfigurationProp appProperties;

	// The JWT signature algorithm used
	final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

	private Boolean isUserValid(final UserDetails userDetails) {
		final String username = userDetails.getUsername();
		final String password = userDetails.getPassword();
		if (null != username && !"".equals(username) && passwordEncoder.matches(username, appProperties.getUsername())
				&& null != password && !"".equals(password)
				&& passwordEncoder.matches(password, appProperties.getPassword())) {
			return true;
		}
		return false;
	}

	private String createToken(final UserDetails userDetails) throws IOException, ServletException {
		// Getting the signing key to sign the JWT Token
		final Key signingKey = getSigningKey();
		// Building JWT Claim here
		JwtBuilder jwtBuilder = Jwts.builder().setId("101").setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject(userDetails.getUsername() + "_aCustomSubject").setIssuer("TechBrunchAuthServer")
				.signWith(signatureAlgorithm, signingKey)
				.setExpiration(new Date(System.currentTimeMillis() + Long.valueOf(appProperties.getExpirationtime())));
		// Building the JWT Token and serializing it to a compact, URL-safe string
		final String jwtToken = jwtBuilder.compact();
		return jwtToken;
	}

	@SuppressWarnings("static-access")
	public ResponseEntity<UserDetails> validateTokenAndReturnResponse(final UserDetails userDetails) {
		ResponseEntity<UserDetails> webServiceResponse;
		try {
			final Key signingKey = getSigningKey();
			final String providedJwtToken = userDetails.getJwtToken();
			// Extracting claims from the provided JWT Token
			final Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(providedJwtToken).getBody();
			// Validating the claim
			if (claims.getId().equals("101") && claims.getIssuer().equals("TechBrunchAuthServer")
					&& claims.getSubject().endsWith("_aCustomSubject")
					&& claims.getExpiration().compareTo(new Date(System.currentTimeMillis())) >= 0) {
				webServiceResponse = new ResponseEntity<UserDetails>(new UserDetails(), HttpStatus.OK);
				webServiceResponse.getBody().setIsJwtTokenValid(true);
				webServiceResponse.getBody().setJwtToken(providedJwtToken);
			} else {
				throw new RuntimeException("Token Validation failed.");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
			webServiceResponse = new ResponseEntity<UserDetails>(new UserDetails(), HttpStatus.BAD_REQUEST);
			webServiceResponse.getBody().setIsJwtTokenValid(false);
		}
		return webServiceResponse;
	}

	@SuppressWarnings("static-access")
	public ResponseEntity<UserDetails> performValidationAndReturnToken(final UserDetails userDetails) {
		ResponseEntity<UserDetails> webServiceResponse;
		try {
			final Boolean isUserValid = isUserValid(userDetails);
			if (isUserValid) {
				webServiceResponse = new ResponseEntity<UserDetails>(new UserDetails(), HttpStatus.OK);
				final String jwtToken = createToken(userDetails);
				System.out.println("Generated Token is: " + jwtToken);
				webServiceResponse.getBody().setUsername(userDetails.getUsername());
				webServiceResponse.getBody().setJwtToken(jwtToken);
			} else {
				throw new RuntimeException("Username/Password is not valid. Cannot generate token");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
			webServiceResponse = new ResponseEntity<UserDetails>(new UserDetails(), HttpStatus.BAD_REQUEST);
		}
		return webServiceResponse;
	}

	private Key getSigningKey() {
		// Preparing Signing key for Signing our JWT with ApiKey secret key
		final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(appProperties.getSecretkey());
		final Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		return signingKey;
	}
}
