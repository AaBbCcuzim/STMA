package com.yx.beans;

public class Student {
	private String id;
	private String name;
	private String gender;
	private String school;
	private int score;
	private int age;
	
	public Student(String id,String name,String gender,String school,int score,int age) {
		this.id=id;
		this.name=name;
		this.gender=gender;
		this.school=school;
		this.score=score;
		this.age=age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	
}
