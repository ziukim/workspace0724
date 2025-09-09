package com.kh.review.oop4;

public class TriangleController {
	private Shape s;
	
	public double calcArea(double height, double width) {
		this.s = new Shape(3, height, width);
		return s.getHeight() * s.getWidth()/2;
	}
	
	public void paintColor(String color) {
		s.setColor(color);
	}
	
	public String print() {
		return "삼각형" + s.information();
	}
}
