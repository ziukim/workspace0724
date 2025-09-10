package com.kh.jdbc.test;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * JDBC를 사용하기 위해서는 자바 프로젝트에 JDBC드라이버를 추가해줘야한다.
 * 프로젝트 우클릭 -> properties -> java build path -> library -> add external jar -> ojdbc.jar 추가
 * 
 * JDBC용 객체
 * - Connection : DB의 연결정보를 담고있는 객체
 * - [Prepared]Statement : 연결된 DB에 sql문을 전달해서 실행하고, 결과를 받아내는 객체
 * - ResultSet : SELECT문 (쿼리)실행 후 조회된 결과를 담는 객체
 */
public class Run1 {
	//각자의 pc(localhost)에 jdbc계정 연결 후 test테이블에 데이터 insert

		public static void main(String[]args) {
			Scanner sc = new Scanner(System.in);
			Connection conn = null; //DB연결정보 보관 객체
			Statement stmt = null; //sql문을 전달해서 실행 후 결과를 받아올 객체
			int result = 0;
			System.out.println("번호 : ");
			int tno = sc.nextInt();
			
			System.out.println("이름 : ");
			String tName = sc.next();
			sc.nextLine();		
				
			//실행할 sql문(완전한 상태로 만듦, sql뒤에는 ;이 없어야 함!!!)
			String sql = "INSERT INTO TEST VALUES("+tno+", '"+tName+"',SYSDATE)";
			
			try  {
				//1) JDBC Driver 등록
				//Class.forName() -> 문자열로 주어진 클래스 이름을 찾아서 JVM에 로드함.
				Class.forName("oracle.jdbc.driver.OracleDriver"); //오타 | ojdbc.jar 파일을 추가하지 않았을 때
				System.out.println("OracleDriver 등록성공");
				
				//127.0.0.1 -> 무조건적으로 지금 실행중인 컴퓨터의 ip(localhost)
				//2) Connection생성(url, 계정명, 비밀번호)
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","C##JDBC","JDBC");
				conn.setAutoCommit(false);
				
				//3)Statement 생성
				stmt = conn.createStatement();
				
				//4, 5) sql문 전달 후 결과를 받음(insert, update, delete -> 처리된 행 수)
				result = stmt.executeUpdate(sql);
				
				//stmt.executeQuery(sql);
				//insert, update, delete -> stmt.executeUpdate : int
				//select -> stmt.executeQuery : ResultSet
				
				//6)트랜잭션 처리
				if(result > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					//7) 다 쓴 자원 반납(생성의 역순)
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			}
			sc.close();
			
			if(result > 0) {
				System.out.println("데이터 추가 성공");
			} else {
				System.out.println("데이터 추가 실패");
			}

		}
}