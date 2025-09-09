package com.kh.example.gearrent1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.kh.example.gearrent.Camera;
import com.kh.example.gearrent.Device;
import com.kh.example.gearrent.GearRentController;
import com.kh.example.gearrent.Laptop;
import com.kh.example.gearrent.Loan;
import com.kh.example.gearrent.Member;

public class GearRentMenu {
	private GearRentController gc = new GearRentController();
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		while(true) {
			System.out.println("=== KH 장비 대여 관리 ===");
			System.out.println("1) 장비등록  2) 회원등록  3) 대여  4) 반남  5) 태그검색");
			System.out.println("6) 키워드검색  7) 전체장비 8) 대여중목록  9) 종료");
			System.out.print("메뉴 : ");
			
			int sel = sc.nextInt();
			sc.nextLine();
			
			switch(sel) {
				case 1: addDevice(); break;
				case 2: addMember(); break;
				case 3: borrow(); break;
				case 4: returnItem(); break;
				case 5: findByTag(); break;
				case 6: findByKeyword(); break;
				case 7: printAllDevices(); break;
				case 8: printActiveLoans(); break;
				case 9: System.out.println("종료합니다."); return;
				default: System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
				}
			System.out.println();
		}
	}
	
	public void addDevice() {
		System.out.print("유형(1:Camera, 2:Laptop) : ");
		int type = sc.nextInt();
	
		System.out.print("장비ID : ");
		String id = sc.next();
		
		System.out.print("장비명 : ");
		String name = sc.next();
		
		System.out.print("분류 : ");
		String category = sc.next();
		
		System.out.print("태그(쉼표로 구분) : ");
		String tag = sc.next();
		sc.nextLine();
		
		Set<String> tags = new HashSet<>();
		for(String s : tag.split(",")) {
			tags.add(s.trim());
		}
		
		Device device;
		switch(type) {
			case 1:
				device = new Camera(id, name, category, tags);
				break;
			case 2:
				device = new Laptop(id, name, category, tags);
			default:
				System.out.println("유형을 잘못 입력하셨습니다.");
				return;
		}
		
		boolean isOk = gc.addDevice(device);
		System.out.println(isOk ? "등록 완료" : "중복된 ID입니다. 다시 입력해주세요.");	
	}
	
	public void addMember() {
		System.out.print("회원ID : ");
		String id = sc.next();
		
		System.out.print("이름 : ");
		String name = sc.next();
		sc.nextLine();
		
		boolean isOk = gc.addMember(new Member(id, name));
		System.out.println(isOk ? "가입 완료" : "중복된 ID입니다. 다시 입력해주세요.");	
	}
	
	public void borrow() {

	}
	
	public void returnItem() {
		
	}
	
	public void findByTag() {
		
	}
	
	public void findByKeyword() {
		
	}
	
	public void printAllDevices() {
		Collection<Device> all = gc.getAllDevices();
		
		if(all.isEmpty()) {
			System.out.println("등록 장비 없음");
		} else {
			for(Device d : all) {
				System.out.println(d);
			}
		}
	}
	
	public void printActiveLoans() {
		Collection<Loan> all = gc.getActiveLoans();
		
		if(all.isEmpty()) {
			System.out.println("대여중 목록 없음");
		} else {
			for(Loan l : all) {
				System.out.println(l);
			}
		}
	}
}










