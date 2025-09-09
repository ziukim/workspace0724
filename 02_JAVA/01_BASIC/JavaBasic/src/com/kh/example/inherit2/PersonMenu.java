package com.kh.example.inherit2;

import java.util.Scanner;

public class PersonMenu {
    private Scanner sc = new Scanner(System.in);
    private PersonController pc = new PersonController();

    public void mainMenu() {
        while (true) {
            int[] cnt = pc.personCount();
            System.out.println("학생은 최대 3명까지 저장할 수 있습니다.");
            System.out.println("현재 저장된 학생은 " + cnt[0] + "명입니다.");
            System.out.println("사원은 최대 10명까지 저장할 수 있습니다.");
            System.out.println("현재 저장된 사원은 " + cnt[1] + "명입니다.");
            System.out.println();
            System.out.println("1. 학생 메뉴");
            System.out.println("2. 사원 메뉴");
            System.out.println("9. 끝내기");
            System.out.print("메뉴 번호 : ");

            int sel = readInt();
            switch (sel) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    employeeMenu();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
            System.out.println();
        }
    }

    public void studentMenu() {
        while (true) {
            System.out.println();
            System.out.println("1. 학생 추가");
            System.out.println("2. 학생 보기");
            System.out.println("9. 메인으로");
            System.out.print("메뉴 번호 : ");

            int sel = readInt();
            switch (sel) {
                case 1:
                    insertStudent();
                    break;
                case 2:
                    printStudent();
                    break;
                case 9:
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

    public void employeeMenu() {
        while (true) {
            System.out.println();
            System.out.println("1. 사원 추가");
            System.out.println("2. 사원 보기");
            System.out.println("9. 메인으로");
            System.out.print("메뉴 번호 : ");

            int sel = readInt();
            switch (sel) {
                case 1:
                    insertEmployee();
                    break;
                case 2:
                    printEmployee();
                    break;
                case 9:
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }

    public void insertStudent() {
        while (true) {
            int[] cnt = pc.personCount();
            if (cnt[0] >= 3) {
                System.out.println("학생을 담을 수 있는 공간이 꽉 찼기 때문에 학생 추가를 종료하고 학생 메뉴로 돌아갑니다.");
                return;
            }

            System.out.print("학생 이름 : ");
            String name = sc.nextLine().trim();
            System.out.print("학생 나이 : ");
            int age = readInt();
            System.out.print("학생 키 : ");
            double height = readDouble();
            System.out.print("학생 몸무게 : ");
            double weight = readDouble();
            System.out.print("학생 학년 : ");
            int grade = readInt();
            System.out.print("학생 전공 : ");
            String major = sc.nextLine().trim();

            pc.insertStudent(name, age, height, weight, grade, major);

            System.out.print("그만하시려면 N(또는 n), 이어하시려면 아무 키나 누르세요 : ");
            String yn = sc.nextLine();
            if (yn.equalsIgnoreCase("n")) return;
        }
    }

    public void printStudent() {
        Student[] arr = pc.printStudent();
        System.out.println("== 학생 목록 ==");
        boolean empty = true;
        for (Student st : arr) {
            if (st != null) {
                System.out.println(st);
                empty = false;
            }
        }
        if (empty) System.out.println("저장된 학생 정보가 없습니다.");
    }

    public void insertEmployee() {
        while (true) {
            int[] cnt = pc.personCount();
            if (cnt[1] >= 10) {
                System.out.println("사원을 담을 수 있는 공간이 꽉 찼기 때문에 사원 추가를 종료하고 사원 메뉴로 돌아갑니다.");
                return;
            }

            System.out.print("사원 이름 : ");
            String name = sc.nextLine().trim();
            System.out.print("사원 나이 : ");
            int age = readInt();
            System.out.print("사원 키 : ");
            double height = readDouble();
            System.out.print("사원 몸무게 : ");
            double weight = readDouble();
            System.out.print("사원 급여 : ");
            int salary = readInt();
            System.out.print("사원 부서 : ");
            String dept = sc.nextLine().trim();

            pc.insertEmployee(name, age, height, weight, salary, dept);

            System.out.print("그만하시려면 N(또는 n), 이어하시려면 아무 키나 누르세요 : ");
            String yn = sc.nextLine();
            if (yn.equalsIgnoreCase("n")) return;
        }
    }

    public void printEmployee() {
        Employee[] arr = pc.printEmployee();
        System.out.println("== 사원 목록 ==");
        boolean empty = true;
        for (Employee em : arr) {
            if (em != null) {
                System.out.println(em.toString());
                empty = false;
            }
        }
        if (empty) System.out.println("저장된 사원 정보가 없습니다.");
    }

    // ---- 입력 유틸 ----
    private int readInt() {
        while (true) {
            try {
                String s = sc.nextLine().trim();
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("정수를 입력하세요 : ");
            }
        }
    }

    private double readDouble() {
        while (true) {
            try {
                String s = sc.nextLine().trim();
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.print("실수를 입력하세요 : ");
            }
        }
    }
}
