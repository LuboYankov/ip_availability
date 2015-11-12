package org.elsysbg.ip.availability;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CommandHandler {
	
	private Map<String, User> USERS = new HashMap<String, User>();
	private Socket socket;
	
	public CommandHandler(Socket socket) {
		this.socket = socket;
	}
	
	public String[] parseCommand(String string) {
		String[] commands = string.split(":");
		return commands;
	}
	
	public String checkCommand(String[] commands) {
		switch(commands[1]) {
			case "login": 
				return this.login(commands);
			case "logout":
				return this.logout(commands);
			case "info": 
				return this.info(commands);
			case "listabsent":
				return this.listabsent(commands);
			case "listavailable":
				return this.listavailable(commands);
			default:
				return "error:unknowncommand:" + commands[1];
		}
	}
	
	public String login(String[] commands) {
		if(!USERS.containsKey(commands[0])) {
			USERS.put(commands[0], new User(commands[0], socket));
		}
		return USERS.get(commands[0]).login();
	}
	
	public String logout(String[] commands) {
		if(USERS.containsKey(commands[0])) {
			return USERS.get(commands[0]).logout();
		} else {
			return "error:alreadyloggedout";
		}
	}
	
	public String info(String[] commands) {
		if(USERS.containsKey(commands[2])) {
			return USERS.get(commands[2]).info();
		} else {
			return "error:noinfo";
		}
	}
	
	public String listabsent(String[] commands) {
		if(!USERS.containsKey(commands[0])) {
			return "error:notloggedin";
		}
		return this.absentUsers();
	}
	
	public String listavailable(String[] commands) {
		if(!USERS.containsKey(commands[0])) {
			return "error:notloggedin";
		}
		return this.availableUsers();
	}
	
	public String absentUsers() {
		String string = "ok";
		for (Entry<String, User> entry : USERS.entrySet()) {
		    if(!entry.getValue().isIn()) 
		    	string += ":" + entry.getKey();
		}
		return string;
	}
	
	public String availableUsers() {
		String string = "ok";
		for (Entry<String, User> entry : USERS.entrySet()) {
		    if(entry.getValue().isIn()) 
		    	string += ":" + entry.getKey();
		}
		return string;
	}
	
}
