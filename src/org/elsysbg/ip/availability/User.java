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
	
	public String login() {
		this.in = true;
		this.loginCount++;
		return "ok";
	}
	
	public String logout() {
		if(isIn()) {
			return "error:alreadyloggedin";
		}
		this.in = false;
		return "ok";
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
