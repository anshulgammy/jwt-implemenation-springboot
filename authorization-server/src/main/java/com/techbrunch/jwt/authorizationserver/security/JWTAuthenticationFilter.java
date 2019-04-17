package com.techbrunch.jwt.authorizationserver.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techbrunch.jwt.authorizationserver.ApplicationConfigurationProp;
import com.techbrunch.jwt.authorizationserver.model.UserDetails;
import com.techbrunch.jwt.authorizationserver.model.ValidateTokenResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author TechBrunch This class responsible for the authentication process *
 */
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Autowired
	private ApplicationConfigurationProp appProperties;

	public JWTAuthenticationFilter() {
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			if (null == passwordEncoder || null == appProperties) {
				final ServletContext servletContext = request.getServletContext();
				final WebApplicationContext webApplicationContext = WebApplicationContextUtils
						.getWebApplicationContext(servletContext);
				passwordEncoder = webApplicationContext.getBean(PasswordEncoder.class);
				appProperties = webApplicationContext.getBean(ApplicationConfigurationProp.class);
			}
			UserDetails userDetails = new ObjectMapper().readValue(request.getInputStream(), UserDetails.class);
			final Boolean isUserValid = isUserValid(userDetails);
			if (isUserValid
					&& (null == request.getHeader("Authorization") || "".equals(request.getHeader("Authorization")))) {
				// generate the token and return the token
				createTokenAndAddToHeader(request, response, filterChain, userDetails);
			} else if (isUserValid
					&& (null != request.getHeader("Authorization") || !"".equals(request.getHeader("Authorization")))) {
				validateTokenAndReturnResponse(request, response);
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
		if (null != username && !"".equals(username) && passwordEncoder.matches(username, appProperties.getUsername())
				&& null != password && !"".equals(password)
				&& passwordEncoder.matches(password, appProperties.getPassword())) {
			return true;
		}
		return false;
	}

	private void createTokenAndAddToHeader(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			UserDetails userDetails) throws IOException, ServletException {
		String token = Jwts.builder().setSubject(userDetails.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + Long.valueOf(appProperties.getExpirationtime())))
				.signWith(SignatureAlgorithm.HS512, appProperties.getSecretkey()).compact();
		res.addHeader("Authorization", token);
	}

	private void validateTokenAndReturnResponse(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		response.setContentType("application/json");
		final PrintWriter out = response.getWriter();
		final ValidateTokenResponse responseObj = new ValidateTokenResponse();
		responseObj.setTokenString(request.getHeader("Authorization"));
		new ObjectMapper().writeValue(out, responseObj);
		out.flush();
	}

}
