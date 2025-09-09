package com.kh.example.oop2;

public class Run {

	public static void main(String[] args) {
		System.out.println(new Student().information());
		
		Student st = new Student();
		st.setClassroom(0);
		
		System.out.println(st.getClassroom());
		System.out.println(st.information());
		
	}

}
