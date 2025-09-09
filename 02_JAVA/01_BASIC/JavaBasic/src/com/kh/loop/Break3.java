package com.kh.loop;

import java.util.Scanner;

public class Break3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//사용자에게 문자열을 입력받아 해당 문자열의 길이를 출력하는 프로그램 만들기
		//단, 사용자가 "exit"을 입력할 때까지 해당 행위를 반복
		String str;
		
		while(true) {
			System.out.println("문자열 입력 : ");
			str = sc.next();
			
			if(str.equals("exit")) {
				break;
			}
			
			System.out.println("길이 : " + str.length());
		}
	}
}
