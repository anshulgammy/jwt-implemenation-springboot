package com.techbrunch.jwt.app.webservice.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.techbrunch.jwt.app.webservice.model.Student;

@Component
public class StudentDaoImpl implements StudentDao {
	final List<Student> listOfStudents = new ArrayList<Student>();
	
	public StudentDaoImpl() {
		this.listOfStudents.add(new Student(1L, "Anshul Gautam", "X-C"));
		this.listOfStudents.add(new Student(2L, "Abhijeet Mishra", "X-A"));
		this.listOfStudents.add(new Student(3L, "Ranjit Mishra", "X-B"));
	}

	public List<Student> getAllStudent() {
		return listOfStudents;
	}

	public Student getStudent(final Long rollNumber) {
		final Student student = findStudentByRollNumber(rollNumber);
		return student;
	}
	
	public List<Student> addStudent(final Student student) {
		this.listOfStudents.add(student);
		return this.listOfStudents;
	}
	
	private Student findStudentByRollNumber(final Long rollNumber) {
		for(Student student : listOfStudents) {
			if(student.getRollNumber().longValue() == rollNumber) {
				return student;
			}
		}
		return null;
	}
	
	public void deleteStudent(final Long rollNumber) {
		final Student student = findStudentByRollNumber(rollNumber);
		this.listOfStudents.remove(student);
	}
}
