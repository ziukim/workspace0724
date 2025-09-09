package com.kh.practice.loop;

import java.util.Scanner;

public class Practice5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sum = 0;
		
		while(true) {
			System.out.println("숫자 입력 : ");
			int num = sc.nextInt();
			
			if(num < 0) {
				break; //음수 입력 시 종료
			}
			
			sum += num;
		}
		
		System.out.println("총합 : " + sum);
		sc.close();
	}

}
