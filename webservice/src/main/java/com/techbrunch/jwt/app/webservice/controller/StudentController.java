package com.techbrunch.jwt.app.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techbrunch.jwt.app.webservice.model.Student;
import com.techbrunch.jwt.app.webservice.service.StudentService;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping(method = RequestMethod.POST, value = "/all")
	public ResponseEntity<?> getAllStudent(@RequestHeader("Authorization") String token) {
		if (studentService.validateJwtTokenAndRespond(token)) {
			System.out.println("::" + "inside getAllStudent");
			final ResponseEntity<List<Student>> response = new ResponseEntity<List<Student>>(
					studentService.getAllStudent(), HttpStatus.OK);
			return response;
		} else {
			final ResponseEntity<String> response = new ResponseEntity<String>("JWT Token used is not proper",
					HttpStatus.OK);
			return response;
		}
	}

	@RequestMapping(value = "/roll/{rollNum}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable(value = "rollNum") long rollNumber, @RequestHeader("Authorization") String token) {
		return studentService.getStudent(Long.valueOf(rollNumber));
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public List<Student> addStudent(Student newStudent, @RequestHeader("Authorization") String token) {
		return studentService.addStudent(newStudent);
	}

	@RequestMapping(value = "/delete/{rollNum}", method = RequestMethod.DELETE)
	public void deleteStudent(@PathVariable(value = "rollNum") long rollNumber, @RequestHeader("Authorization") String token) {
		studentService.deleteStudent(Long.valueOf(rollNumber));
	}

}
