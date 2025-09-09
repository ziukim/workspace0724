package com.kh.example.gearrent1;

import java.util.Set;

public class Laptop extends Device{

	public Laptop(String id, String name, String category, Set<String> tags) {
		super(id, name, category, tags);
	}

	@Override
	public int getBorrowLimitDays() {
		return 14;
	}

	@Override
	public int calcLateFee(int overdueDays) {
		return overdueDays * 500;
	}
	
	
}
