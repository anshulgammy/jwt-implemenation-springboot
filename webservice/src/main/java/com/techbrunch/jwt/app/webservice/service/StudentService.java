package com.techbrunch.jwt.app.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbrunch.jwt.app.webservice.dao.StudentDao;
import com.techbrunch.jwt.app.webservice.model.Student;

@Service
public class StudentService {
	
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
}
