package com.kh.jdbc.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Run2 {
	//각자의 pc(localhost)에 jdbc계정 연결 후 test테이블의 데이터를 select
	//select -> 결과 : ResultSet -> 데이터를 추출
	public static void main(String[]args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<Test> list = new ArrayList<>();
		
		//실행할 sql
		String sql = "SELECT * FROM TEST ORDER BY tno";
				
		try {
			//1) JDBC드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("OracleDriver 등록 완료");
			
			//2) Connection생성(db, url, 계정명, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JDBC", "JDBC");
			
			//3) Statement 생성
			stmt = conn.createStatement();
			
			//4,5) sql문 전달하면서 결과받아(select -> ResultSet)
			rset = stmt.executeQuery(sql);
			
			//rset.next() -> rset의 다음행이 있는지 없는지를 알려주고 + 다음행을 가르킨다.
			while(rset.next()) {
				int tno = rset.getInt("TNO");
				String tName = rset.getString("TNAME");
				Date tDate = rset.getDate("TDATE");
				
				list.add(new Test(tno, tName, tDate.toLocalDate()));
			}
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(list.isEmpty()) {
			System.out.println("데이터가 없습니다.");
		} else {
			System.out.println(list);
		}
	}	
}
