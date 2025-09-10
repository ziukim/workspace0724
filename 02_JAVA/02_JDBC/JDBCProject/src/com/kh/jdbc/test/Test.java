package com.kh.jdbc.test;

import java.time.LocalDate;
import java.util.Objects;

public class Test {
	private int tno;
	private String tname;
	private LocalDate tdate;
	
	public Test() {
		super();
	}

	public Test(int tno, String tname, LocalDate tdate) {
		super();
		this.tno = tno;
		this.tname = tname;
		this.tdate = tdate;
	}

	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public LocalDate getTdate() {
		return tdate;
	}

	public void setTdate(LocalDate tdate) {
		this.tdate = tdate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tno);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Test) {
			return((Test)obj).getTno() == tno;
		}
		return false;
	}

	@Override
	public String toString() {
		return "[tno=" + tno + ",tname=" + tname + ",tdate=" + tdate + "]";
	}
	
	
}
