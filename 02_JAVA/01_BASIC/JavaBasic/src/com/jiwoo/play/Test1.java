package com.jiwoo.play;

public class Test1 {

	public static void main(String[] args) {
		System.out.println("시작");
		helloEveryone(12);
		helloEveryone(13);
		isMyname("김지우");
		divide(1235, 4);
		isMyname2("김시우");
		System.out.println("끝");
	}
	
	public static void helloEveryone(int age) {
		System.out.println("안녕");
		System.out.println("제 나이는 " + age + "세 입니다.");
	}
	
	public static void isMyname(String name) {
		System.out.println("안녕");
		System.out.println("내 이름은 " + name + " 이다");
	}
	
	public static void divide(int num1, int num2) {
		if(num2 == 0) {
			System.out.println("0으로 나눌 수 없습니다");
			return;
		}	
		System.out.println("나눗셈 결과 :" + ((double)num1/num2));
	}
	
	public static void isMyname2(String name) {
		System.out.println(name);
	}
	
}
