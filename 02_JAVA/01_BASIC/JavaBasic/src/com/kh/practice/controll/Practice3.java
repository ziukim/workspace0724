package com.kh.practice.controll;

import java.util.Scanner;

public class Practice3 {
	/*
	 * 어린이, 청소년, 성인의 구분에 따라 입장료가 다르게 부과되는 놀이공원 요금 계산기
	 * 주말에는 20% 할인이 적용 됨.
	 * 
	 * [입력조건]
	 * 나이(int)
	 * 요일(문자열 : "월", "화", ..., "일")
	 * 
	 * [요금표]
	 * 어린이 : 0 ~ 12세 -> 5000원
	 * 청소년 : 13 ~ 18세 -> 7000원
	 * 성인 : 19세 이상 -> 10000원
	 * 
	 * [할인조건]
	 * 요일이 "토" 또는 "일" 인 경우, 입장료의 20% 할인
	 * 
	 * [출력]
	 * 나이를 입력하세요 : xx
	 * 요일을 입력하세요 : x
	 * 
	 * 청소년 요금입니다. (주말할인적용)
	 * 최종 요금은 xxxx원입니다.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int age;
		
		System.out.print("나이를 입력하세요 : ");
		age = sc.nextInt();
		System.out.print("요일을 입력하세요 : ");
		String day = sc.next();
		
		if(age <= 12) {
			System.out.print("어린이 요금입니다.");
			if(day.equals("토") || day.equals("일")) {
				System.out.println("(주말 할인 적용)\n 최종 요금은 4000원 입니다.");
			} else {
				System.out.println("\n최종 요금은 5000원 입니다.");
			}
		} else if(age <= 18) {
			System.out.print("청소년 요금입니다.");
			if(day.equals("토") || day.equals("일")) {
				System.out.println("(주말 할인 적용)\n 최종 요금은 5600원 입니다.");
			} else {
				System.out.println("\n최종 요금은 7000원 입니다.");
			}
		} else {
			System.out.print("성인 요금입니다.");
			if(day.equals("토") || day.equals("일")) {
				System.out.println("(주말 할인 적용)\n 최종 요금은 8000원 입니다.");
			} else {
				System.out.println("\n최종 요금은 10000원 입니다.");
			}
		}
	}

}












