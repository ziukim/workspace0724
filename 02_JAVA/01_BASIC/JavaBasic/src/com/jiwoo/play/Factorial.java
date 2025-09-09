package com.jiwoo.play;

import java.util.Scanner;

public class Factorial {
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("3 factorial :" + factorial(3));
		System.out.println("12 factorial :" + factorial(12));
		System.out.println("factorial? : ");
		int fac = sc.nextInt();
		System.out.println(factorial(fac));
		
	}

	public static long factorial(long n) {
		if(n == 1)
			return 1;
		else
			return n * factorial(n-1);
	}
}
