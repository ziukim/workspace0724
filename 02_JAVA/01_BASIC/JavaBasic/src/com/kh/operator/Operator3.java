package com.kh.operator;

public class Operator3 {
	/*
	 * 산술연산자
	 * + - / * %
	 * 
	 * 복합대입연산자
	 * 산술연산자의 대입연산자를 열함해서 사용할 수 있다
	 * 
	 * +=, /=, -=, %=, *=
	 * 
	 * a = a % 3; -> a %= 3;
	 * */
	public static void main(String[] args) {
		int a = 5;
		int b = 10;
		int c = (++a) + b; // a = 6, b = 10, c = 16
		int d = 16 / 6; // d = 2
		int e = c % a; // e = 4
		int f = e++; // f = 4, e = 5
		int g = (--b) + (d--); //b = 9, d = 1, g = 11
		int h = 2;
		
		int i = (a++) + b / (--c / f) * (g-- -d) & (++e + h);
		
		System.out.println(i);
	}

}
