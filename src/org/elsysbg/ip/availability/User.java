package org.elsysbg.ip.availability;

public class User {
	
	private String name;
	private int loginCount;
	private boolean in;
	
	public User(String username) {
		this.name = username;
		this.loginCount = 0;
		this.in = false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getLoginCounts() {
		return this.loginCount;
	}
	
	public boolean isIn() {
		return in;
	}
	
}
