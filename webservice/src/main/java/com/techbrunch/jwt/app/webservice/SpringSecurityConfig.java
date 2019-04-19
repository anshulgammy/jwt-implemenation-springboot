package com.techbrunch.jwt.app.webservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new CustomFilterForCors(), ChannelProcessingFilter.class);
		http.csrf().disable();
	}
}
