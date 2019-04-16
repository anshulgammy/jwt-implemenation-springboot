package com.techbrunch.jwt.app.webservice.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.techbrunch.jwt.app.webservice.model.Student;

@Repository
public interface StudentDao {
	public List<Student> getAllStudent();

	public Student getStudent(final Long rollNumber);

	public List<Student> addStudent(final Student student);

	public void deleteStudent(final Long rollNumber);
}
