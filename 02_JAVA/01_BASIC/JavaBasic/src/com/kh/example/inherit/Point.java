package com.kh.example.inherit;

public class Point {
	private int x;
	private int y;
	
	//alt+shift+s O
	public Point() {
		super();
	}
	
	//alt+shift+s O
	public Point(int x, int y) {
		super(); //생략가능
		this.x = x;
		this.y = y;
	}
	
	//alt+shift+s R
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	//alt+shift+s V 에서 toString
	@Override
	public String toString() {
		return x + "," + y;
	}
	
}
