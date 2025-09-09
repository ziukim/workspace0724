package com.kh.jdbc.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.controller.MemberController;

//View : 사용자와의 상호작용을 하는 객체 -> 입력및 출력
public class MemberMenu {
	private Scanner sc;
	private MemberController mc;

	public MemberMenu() {
		super();
		this.sc = new Scanner(System.in);
		this.mc = new MemberController();
	} 
	
	/*
	 * 사용자가 보게 될 첫 화면(메인화면)
	 */
	public void mainMenu() {
		while(true) {
			System.out.println("======회원관리 프로그램======");
			System.out.println("1. 회원추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 정보 변경");
			System.out.println("4. 회원 탈퇴");
			System.out.println("5. 회원 이름으로 키워드 검색");
//			System.out.println("6. 회원 일괄 추가");
//			System.out.println("7. 회원 일괄 삭제");
			System.out.println("9. 프로그램 종료");
			
			System.out.print("메뉴 입력 : ");
			int sel = sc.nextInt();
			sc.nextLine();
			
			switch(sel) {
				case 1 : insertMember(); break;
				case 2 : mc.selectMemberAll(); break;
				case 3 : updateMember(); break;
				case 4 : deleteMember(); break;
				case 5 : memberSearchByName(); break;
//				case 6 : bulkInsertMembers(); break; //회원추가를 몇 명 할것인지?
//				case 7 : bulkDeleteMembers(); break; //아이디를 ,로 구분해서 입력받아서
				case 9 : 
						System.out.println("프로그램을 종료합니다.");
					return;
					default : System.out.println("잘못 입력하셨습니다. ");
			}
			
			System.out.println();
		}
	}
	
	public void memberSearchByName() {
		System.out.println("=======이름으로 회원검색=======");
		System.out.println("키워드 : ");
		String keyword = sc.nextLine();
		
		mc.memberSearchByName(keyword);
	}
	
	public void deleteMember() {
		System.out.println("========회원 탈퇴========");
		System.out.println("탈퇴할 회원의 아이디 : ");
		String userId = sc.nextLine();
		
		System.out.println("비밀번호 확인 : ");
		String userPwd = sc.nextLine();
		
		mc.deleteMember(userId, userPwd);
	}
	
	public void updateMember() {
		System.out.println("========회원 정보 변경========");
		//어떤 회원의 정보를 수정할 것인가 -> USER_ID
		//변경할 정보를 입력 -> 이메일, 전화번호, 주소, 취미
		
		System.out.println("정보를 수정할 아이디 : ");
		String userId = sc.nextLine();
		
		System.out.println("변경할 이메일 : ");
		String email = sc.nextLine();
		
		System.out.println("변경할 전화번호(-제외) : ");
		String phone = sc.nextLine();
		
		System.out.println("변경할 주소 : ");
		String address = sc.nextLine();
		
		System.out.println("변경할 취미(,로 구분) : ");
		String hobby = sc.nextLine();
		
		mc.updateMember(userId, email, phone, address, hobby);
	}
	
	public void insertMember() {
		System.out.println("========회원 추가========");
		//id~취미까지 전부 입력받아서 회원 추가
		
		System.out.println("아이디 : ");
		String userId = sc.nextLine();
		
		System.out.println("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		System.out.println("이름 : ");
		String userName = sc.nextLine();
		
		System.out.println("성별(M,F) : ");
		String gender = sc.nextLine();
		
		System.out.println("나이 : ");
		String age = sc.nextLine();
				
		System.out.println("이메일 : ");
		String email = sc.nextLine();
		
		System.out.println("전화번호(-제외) : ");
		String phone = sc.nextLine();
		
		System.out.println("주소 : ");
		String address = sc.nextLine();
		
		System.out.println("취미(,로 구분) : ");
		String hobby = sc.nextLine();
		
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
	}

	//=============================응답화면=====================================
	//서비스 요청 처리 후 성공했을 때 사용자가 보게될 화면
	//msg : 기능별 성공메세지
	public void displaySuccess(String msg) {
		System.out.println("\n서비스 요청 성공 : " + msg);
	}
	
	//서비스 요청 처리 후 성공했을 때 사용자가 보게될 화면
	//msg : 기능별 실패메세지
	public void displayFail(String msg) {
		System.out.println("\n서비스 요청 실패 : " + msg);
	}

	public void displayNoData(String msg) {
		System.out.println("\n" + msg);
	}
	
	public void displayList(List list, String title) {
		System.out.println("=======" + title + "=======");
		for(Object o : list) {
			System.out.println(o);
		}
	}

}



