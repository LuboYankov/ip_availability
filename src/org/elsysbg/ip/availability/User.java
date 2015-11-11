package org.elsysbg.ip.availability;

import java.net.Socket;

public class User {
	
	private String name;
	private int loginCount;
	private boolean in;
	private Socket socket;
	
	public User(String username, Socket socket) {
		this.name = username;
		this.loginCount = 0;
		this.in = false;
		this.socket = socket;
	}
	
	public void login() {
		this.in = true;
		this.loginCount++;
	}
	
	public void logout() {
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
