package com.techbrunch.jwt.app.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.techbrunch.jwt.app.webservice.dao.StudentDao;
import com.techbrunch.jwt.app.webservice.model.Student;
import com.techbrunch.jwt.app.webservice.model.UserDetails;

@Service
public class StudentService {

	final RestTemplate restTemplate = new RestTemplate();

	@Autowired
	StudentDao studentDao;

	public List<Student> getAllStudent() {
		return studentDao.getAllStudent();
	}

	public Student getStudent(final Long rollNumber) {
		return studentDao.getStudent(rollNumber);
	}

	public List<Student> addStudent(final Student student) {
		return studentDao.addStudent(student);
	}

	public void deleteStudent(final Long rollNumber) {
		studentDao.deleteStudent(rollNumber);
	}

	public boolean validateJwtTokenAndRespond(String jwtToken) {
		if (null != jwtToken || !"".equals(jwtToken)) {
			jwtToken = jwtToken.replace("Bearer ", "");
			System.out.println("Provided token is: " + jwtToken);
			final UserDetails requestUserDetails = new UserDetails();
			requestUserDetails.setJwtToken(jwtToken);
			requestUserDetails.setJwtToken(jwtToken);
			final HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", "application/json");
			final HttpEntity requestEntity = new HttpEntity(requestUserDetails, headers);
			ResponseEntity<UserDetails> responseEntity = restTemplate.exchange("http://localhost:8585/token/validate",
					HttpMethod.POST, requestEntity, UserDetails.class);
			final UserDetails userDetails = responseEntity.getBody();
			/*
			 * final UserDetails userDetails =
			 * restTemplate.postForObject("http://localhost:8585/token/validate",
			 * requestUserDetails, UserDetails.class);
			 */
			if (null != userDetails && userDetails.getIsJwtTokenValid()) {
				return true;
			}
		}
		return false;
	}
}
