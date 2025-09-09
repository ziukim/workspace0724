package com.kh.example.inherit2;

public class Student extends Person{
	private int grade;
	private String number;
	
	public Student() {
		super();
	}
	
	public Student(String name, int age, double height, double weight, int grade, String number) {
		super(name, age, height, weight);
		this.grade = grade;
		this.number = number;
	}


	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return super.toString() + "," + grade + "," + number;
	}
	
	
	
}
