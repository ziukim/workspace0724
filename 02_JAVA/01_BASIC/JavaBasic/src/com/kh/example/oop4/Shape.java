package com.kh.example.oop4;

public class Shape {
    private int type;        
    private double height;  
    private double width;   
    private String color;   

    
    public Shape() {
        this.color = "white"; 
    }

    // 매개변수 있는 생성자
    public Shape(int type, double height, double width) {
        this.type = type;
        this.height = height;
        this.width = width;
        this.color = "white"; // 기본 색상
    }

    // Getter & Setter
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    // 정보 반환 메서드
    public String information() {
        return height + " " + width + " " + color;
    }
}
