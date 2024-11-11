package model;

import view.*;
import controller.*;

public class Member {
	private String name;
	private String phoneNumber;
	
	public Member(String name, String phoneNumber ) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}
	//check name format
	public boolean setName(String name) {
		if (name.matches("^[0-9A-Za-z\\s-]+$")) {
			return true; }
		return false;
	}

	public String getHp() {
		return phoneNumber;
	}
	//check phone number length
	public boolean setHp(String phoneNumber) {
		if (phoneNumber.length()!= 11)
			return false;
		else
			return true;
	}
	//string to string
	public String toString() {
		return "Member [name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}
	public String toString2() {
		return phoneNumber;
	}

}