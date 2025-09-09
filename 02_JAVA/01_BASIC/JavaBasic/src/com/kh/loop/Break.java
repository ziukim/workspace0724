package com.kh.loop;

import java.util.Scanner;

public class Break {
	/*
	 * break : 반복문, switch문 안에서 사용되는 분기문
	 * 		   break; 가 실행되는 순간 가장 가까운 반복문 또는 switch문을 강제로 탈출한다.
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//랜덤값(1~100)을 발생시켜 출력(이과정을 계속반복)
		//단, 랜덤값이 3의배수(3으로 나누면 0)일 경우 반복문 종료
		while(true) {
			int num = (int)(Math.random() * 100) + 1;
			System.out.println(num);
			if(num % 3 == 0) {
				break;
			}
		}

	}

}
