package com.techbrunch.jwt.app.webservice.model;

import org.springframework.stereotype.Component;

@Component
public class Student {
	private Long rollNumber;
	private String fullName;
	private String classAndSection;

	public Long getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(Long rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getClassAndSection() {
		return classAndSection;
	}

	public void setClassAndSection(String classAndSection) {
		this.classAndSection = classAndSection;
	}

	public Student(Long rollNumber, String fullName, String classAndSection) {
		this.rollNumber = rollNumber;
		this.fullName = fullName;
		this.classAndSection = classAndSection;
	}

	public Student() {
	}
}
