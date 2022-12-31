package com.pevir.StudentManagment.Model;

public class Course {
	
	private String course;
	private String description;
	private String degree;
	public String getCourse() {
		return course;
	}
	public String getDescription() {
		return description;
	}
	public String getDegree() {
		return degree;
	}
	public Course(String course, String description, String degree) {
		super();
		this.course = course;
		this.description = description;
		this.degree = degree;
	}
	
	

}
