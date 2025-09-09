package com.kh.example.inherit;

public class CircleController {
	private Circle c = new Circle();
	
	public String calcArea(int x, int y, int radius) {
		c.setX(x);
		c.setY(y);
		c.setRadius(radius);
		
		double area = Math.PI * radius * radius;
		return c.toString() + "/" + area;
	}
	
	public String calCircum(int x, int y, int radius) {
		c.setX(x);
		c.setY(y);
		c.setRadius(radius);
		
		double circum = Math.PI * c.getRadius() * 2;
		return c.toString() + "/" + circum;
	}
}
