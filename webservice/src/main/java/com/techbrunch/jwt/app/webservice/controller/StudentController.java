package com.techbrunch.jwt.app.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public List<Student> getAllStudent() {
		System.out.println("::" + "inside getAllStudent");
		return studentService.getAllStudent();
	}

	@RequestMapping(value = "/roll/{rollNum}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable(value = "rollNum") long rollNumber) {
		return studentService.getStudent(Long.valueOf(rollNumber));
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public List<Student> addStudent(Student newStudent) {
		return studentService.addStudent(newStudent);
	}

	@RequestMapping(value = "/delete/{rollNum}", method = RequestMethod.DELETE)
	public void deleteStudent(@PathVariable(value = "rollNum") long rollNumber) {
		studentService.deleteStudent(Long.valueOf(rollNumber));
	}

}
