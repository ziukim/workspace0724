package com.kh.practice.controll;

import java.util.Scanner;

public class Practice1 {
	
	/*
	 * 0점부터 100점 사이의 정수를 입력받아 아래 조건에 따라 등급(학점)을 출력하는 프로그램을 작성
	 * 
	 * [입력조건]
	 * 시험 점수 (정수형, 0이상 100이하)
	 * 
	 * [성적 기준표]
	 * 90~100 : A
	 * 80~89 : B
	 * 70~79 : C
	 * 60~69 : D
	 * 0~59 : F
	 * 
	 * [출력 예시]
	 * 점수를 입력하세요 : xx
	 * 당신의 성적은 x 입니다.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int score;
		System.out.print("점수를 입력하세요 : ");
		score = sc.nextInt();
		
		if(score <= 100 && score >= 90) {
			System.out.println("당신의 성적은 A 입니다.");
		} else if (score >= 80) {
			System.out.println("당신의 성적은 B 입니다.");
		} else if (score >= 70) {
			System.out.println("당신의 성적은 C 입니다.");
		} else if (score >= 60) {
			System.out.println("당신의 성적은 D 입니다.");
		} else {
			System.out.println("당신의 성적은 F 입니다.");
		}
		
		
	}

}









