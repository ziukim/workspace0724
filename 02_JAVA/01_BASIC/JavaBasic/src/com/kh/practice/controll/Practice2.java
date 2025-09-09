package com.kh.practice.controll;

import java.util.Scanner;

public class Practice2 {
	/*
	 * 1부터6까지 눈이 있는 세 개의 주사위를 던졌을 때, 아래 규칙에 따라 상금을 계산하는 프로그램을 작성하세요
	 * 
	 * [입력조건]
	 * 한 줄에 세 개의 정수(각각 주사위 눈, 1~6)공백으로 구분하여 입력
	 * 
	 * [상금 계산 규칙]
	 * 1 : 같은 눈이 세 개 모두 같을 때 -> 10000원 + 같은눈 * 1000원
	 * 2 : 같은 눈이 두 개만 같을 때 -> 1000원 + 같은눈 * 100원
	 * 3 : 모두 다른 눈일 때 -> 최댓값  * 100원
	 * 
	 * [출력예시]
	 * 주사위 값 입력 :
	 * 상금 : 
	 */
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num1, num2, num3;
		System.out.print("주사위 값 입력 :");
		num1 = sc.nextInt();
		num2 = sc.nextInt();
		num3 = sc.nextInt();
		
		if(num1 == num2 && num1 == num3) {
			System.out.println("상금 : " + (10000 + num1 * 1000));
		} else if (num1 == num2 && num1 != num3) {
			System.out.println("상금 : " + (1000 + num1 * 100));
		} else if (num1 == num3 && num1 != num2) {
			System.out.println("상금 : " + (1000 + num1 * 100));
		} else if (num2 == num3 && num1 != num2) {
			System.out.println("상금 : " + (1000 + num2 * 100));
		} else {
			if (num1 >= num2 && num1 >= num3) {
				System.out.println("상금 : " + num1 * 100);
			} else if (num2 >= num1 && num2 >= num3) {
				System.out.println("상금 : " + num2 * 100);
			} else {
				System.out.println("상금 : " + num3 * 100);
			}
		}
	
		
	}

}
