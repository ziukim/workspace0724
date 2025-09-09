package com.kh.example.poly1;

public class Animal {
	public String name;
	public int age;
	
	public Animal() {
		super();
	}

	public Animal(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void speak() {
		System.out.println("동물이 소리를 냅니다");
	}
	
}
