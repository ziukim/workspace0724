package com.kh.practice.controll;

public class Practiceloop1 {
	
	public static void main(String[] args) {
		
		int dan = 1;
		
		for(int i=1; i<=9; i++) {
			System.out.printf("%d * %d = %d\n", dan, i, (dan * i));
		}
		
		//1~9 단까지 반복
		for(int j=2; j<=9; j++) {
			for(int i=1; i<=9; i++) {
				System.out.printf("%d * %d = %d\n", j, i, (j * i));
			}
		}
		
	}

}
