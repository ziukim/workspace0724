package com.kh.example.oop4;

import java.util.Scanner;

public class ShapeMenu {
    private Scanner sc = new Scanner(System.in);
    private SquareController scr = new SquareController();
    private TriangleController tc = new TriangleController();

    public void inputMenu() {
        while (true) {
            System.out.println("===== 도형 프로그램 =====");
            System.out.println("3. 삼각형");
            System.out.println("4. 사각형");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 번호 : ");
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 3: triangleMenu(); break;
                case 4: squareMenu(); break;
                case 9: System.out.println("프로그램 종료"); return;
                default: System.out.println("잘못 입력");
            }
        }
    }

    public void triangleMenu() {
        while (true) {
            System.out.println("===== 삼각형 =====");
            System.out.println("1. 삼각형 면적");
            System.out.println("2. 삼각형 색칠");
            System.out.println("3. 삼각형 정보");
            System.out.println("9. 메인으로");
            System.out.print("메뉴번호 : ");
            int menuNum = sc.nextInt();

            if (menuNum == 9) return;
            inputSize(3, menuNum);
        }
    }

    public void squareMenu() {
        while (true) {
            System.out.println("===== 사각형 =====");
            System.out.println("1. 사각형 둘레");
            System.out.println("2. 사각형 면적");
            System.out.println("3. 사각형 색칠");
            System.out.println("4. 사각형 정보");
            System.out.println("9. 메인으로");
            System.out.print("메뉴번호 : ");
            int menuNum = sc.nextInt();

            if (menuNum == 9) return;
            inputSize(4, menuNum);
        }
    }

    public void inputSize(int type, int menuNum) {
        if (type == 3) { // 삼각형
            switch (menuNum) {
                case 1:
                    System.out.print("높이 : ");
                    double th = sc.nextDouble();
                    System.out.print("너비 : ");
                    double tw = sc.nextDouble();
                    System.out.println("면적 : " + tc.calcArea(th, tw));
                    break;
                case 2:
                    System.out.print("색상 입력 : ");
                    String tcColor = sc.next();
                    tc.paintColor(tcColor);
                    break;
                case 3:
                    printInformation(3);
                    break;
            }
        } else if (type == 4) { // 사각형
            switch (menuNum) {
                case 1:
                    System.out.print("높이 : ");
                    double sh1 = sc.nextDouble();
                    System.out.print("너비 : ");
                    double sw1 = sc.nextDouble();
                    System.out.println("둘레 : " + scr.calcPerimeter(sh1, sw1));
                    break;
                case 2:
                    System.out.print("높이 : ");
                    double sh2 = sc.nextDouble();
                    System.out.print("너비 : ");
                    double sw2 = sc.nextDouble();
                    System.out.println("면적 : " + scr.calcArea(sh2, sw2));
                    break;
                case 3:
                    System.out.print("색상 입력 : ");
                    String scColor = sc.next();
                    scr.paintColor(scColor);
                    break;
                case 4:
                    printInformation(4);
                    break;
            }
        }
    }

    public void printInformation(int type) {
        if (type == 3) {
            System.out.println(tc.print());
        } else if (type == 4) {
            System.out.println(scr.print());
        }
    }
}
