package com.kh.loop;

import java.util.Scanner;

public class Break2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i<6; i++) {
			int num = (int)(Math.random() * 45) + 1;
			System.out.println(num + " ");
		
		}
		
		System.out.println("------");
		
		for(int u=0; ;u++) {
			int num = (int)(Math.random() * 45) + 1;
			System.out.println(num + " ");
			if(u == 5) {
				break;
			}
		
		}
	}

}
