package com.pevir.StudentManagment.Model;

import java.sql.Date;

public class Student {
	private Integer studentNum;
	private String years;
	private String course;
	private String firstName;
	private String lastName;
	private String gender;
	private Date birthDate;
	private String statue;
	private String image;
	
	public Integer getStudentNum() {
		return studentNum;
	}

	public String getYears() {
		return years;
	}

	public String getCourse() {
		return course;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getStatue() {
		return statue;
	}

	public String getImage() {
		return image;
	}

	public Student(Integer studentNum, String years, String course, String firstName, String lastName, String gender,
			Date birthDate, String statue, String image) {
		super();
		this.studentNum = studentNum;
		this.years = years;
		this.course = course;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.statue = statue;
		this.image = image;
	}
}
