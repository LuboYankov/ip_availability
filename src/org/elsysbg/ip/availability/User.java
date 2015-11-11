package org.elsysbg.ip.availability;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String name;
	private int loginCount;
	private boolean in;
	private Socket socket;
	private List<String> dates = new ArrayList<String>();
	
	public User(String username, Socket socket) {
		this.name = username;
		this.loginCount = 0;
		this.in = false;
		this.socket = socket;
	}
	
	public String login() {
		if(isIn()) {
			return "error:alreadyloggedin";
		}
		this.in = true;
		this.loginCount++;
		return "ok";
	}
	
	public String logout() {
		if(!isIn()) {
			return "error:alreadyloggedout";
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
