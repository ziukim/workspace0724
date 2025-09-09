package com.kh.example.gearrent1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import com.kh.example.gearrent.Device;
import com.kh.example.gearrent.Loan;
import com.kh.example.gearrent.Member;

public class GearRentController {
	private HashMap<String, Device> catalog = new HashMap<>();
	private HashMap<String, Member> members = new HashMap<>();
	private HashMap<String, Loan> activeLoans = new HashMap<>();
	
	public boolean addDevice(Device device) {
		if(catalog.containsKey(device.getId())) {
			return false;
		}
		
		catalog.put(device.getId(), device);
		return true;
	}
	
	public boolean addMember(Member member) {
		if(members.containsKey(member.getId())) {
			return false;
		}
		
		members.put(member.getId(), member);
		return true;
	}
	
	public Loan borrow(String memberId, String itemId, LocalDate today) {
		return null;
		
	}
	
	public int returnItem(String itemId, LocalDate today) {
		return 0;
		
	}
	
	public ArrayList<Device> findByTag(String tag){
		return null;
		
	}
	
	public ArrayList<Device> findByKeyword(String keyword){
		return null;
		
	}
	
	public Collection<Device> getAllDevices(){
		return Collections.unmodifiableCollection(catalog.values());
	}
	
	public Collection<Loan> getActiveLoans(){
		return Collections.unmodifiableCollection(activeLoans.values());
	}
}








