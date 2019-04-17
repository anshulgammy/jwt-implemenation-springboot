package com.techbrunch.jwt.authorizationserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorizationServerApplicationTests {
	
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testUserName() {
		final PasswordEncoder passwordEncoder = getPasswordEncoder();
		System.out.println("username is : " + passwordEncoder.encode("myUsername"));
		System.out.println("password is : " + passwordEncoder.encode("myPassword"));
	}

}
