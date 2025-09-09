package com.kh.example.oop1;

public class Run {
	
	public static void main(String[]args) {
		Product p1 = new Product();
		p1.setBrand("애플");
		p1.setpName("아이폰16");
		p1.setPrice(2000000);
		
		Product p2 = new Product();
		p2.setBrand("삼성");
		p2.setpName("갤럭시");
		p2.setPrice(1000000);
		
		p1.information();
		p2.information();
		
		System.out.println(p1.getpName());
	}
}
