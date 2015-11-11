package org.elsysbg.ip.availability;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements IUser {
	
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
	
	public Socket getSocket() {
		return socket;
	}
	
	public String login() {
		if(isIn()) {
			return "error:alreadyloggedin";
		}
		this.in = true;
		this.loginCount++;
		this.addDate();
		return "ok";
	}
	
	public String logout() {
		if(!isIn()) {
			return "error:alreadyloggedout";
		}
		this.in = false;
		this.addDate();
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
	
	public String info() {
		return "ok:" + getName() + ":" + isIn() + ":" + getLoginCounts() + datesToString();
	}
	
	public void addDate() {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH'_'mm'_'ss.SSSZ");
		Date date = new Date();
		String dateFormatted = DATE_FORMAT.format(date);
		this.dates.add(dateFormatted);
	}
	
	public String datesToString() {
		String string = "";
		for(int i = 0; i < dates.size(); i++) {
			string += ":" + dates.get(i);
		}
		return string;
	}
	
}
