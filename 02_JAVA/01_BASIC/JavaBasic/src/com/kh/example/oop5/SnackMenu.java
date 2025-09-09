package com.kh.example.oop5;

import java.util.Scanner;

public class SnackMenu {
	private Scanner sc = new Scanner(System.in);
	private SnackController scr = new SnackController();
	
	public void menu() {
		System.out.println("스낵류를입력하세요.");
		System.out.print("종류 : ");
		String kind = sc.next();
		
		System.out.print("이름 : ");
		String name = sc.next();

		
		System.out.print("맛 : ");
		String flavor = sc.next();

		
		System.out.print("개수 : ");
		int numOf = sc.nextInt();
		
		System.out.print("가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		
		System.out.println(scr.saveData(kind, name, flavor, numOf, price));
		System.out.print("저장한 정보를 확인하시겠습니까?(y/n) : ");
		String answer = sc.next();
		
		if(answer.equals("y")) {
			System.out.println(scr.confirmData());
		}

		
	}
}
