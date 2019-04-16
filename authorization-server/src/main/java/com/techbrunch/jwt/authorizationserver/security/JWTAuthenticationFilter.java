package com.techbrunch.jwt.authorizationserver.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techbrunch.jwt.authorizationserver.model.UserDetails;

import io.jsonwebtoken.Jwts;

/**
 * @author TechBrunch This class responsible for the authentication process *
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	UserDetails correctUserDetails;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			UserDetails userDetails = new ObjectMapper().readValue(request.getInputStream(), UserDetails.class);
			final Boolean isUserValid = isUserValid(userDetails);
			if (isUserValid) {
				// generate the token and return the token
				createTokenAndAddToHeader(request, response, filterChain, userDetails);
			} else {
				throw new Exception("Incorrect username/password was provided.");
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private Boolean isUserValid(final UserDetails userDetails) {
		final String username = userDetails.getUsername();
		final String password = userDetails.getPassword();
		if (null != username && !"".equals(username)
				&& passwordEncoder.encode(username).equals(correctUserDetails.getUsername()) && null != password
				&& !"".equals(password) && passwordEncoder.encode(password).equals(correctUserDetails.getPassword())) {
			return true;
		}
		return false;
	}

	private void createTokenAndAddToHeader(HttpServletRequest req, HttpServletResponse res, FilterChain chain, UserDetails userDetails)
			throws IOException, ServletException {
		String token = Jwts.builder().setSubject(userDetailsgetUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}

}
