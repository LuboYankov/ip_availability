package org.elsysbg.ip.availability;

import java.io.IOException;
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
	
	public String checkCommand(String[] commands, Server server) throws IOException {
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
			case "shutdown":
				return this.shutdown(server);
			default:
				return "error:unknowncommand:" + commands[1];
		}
	}
	
	private String login(String[] commands) {
		if(!USERS.containsKey(commands[0])) {
			USERS.put(commands[0], new User(commands[0], socket));
		}
		return USERS.get(commands[0]).login();
	}
	
	private String logout(String[] commands) {
		if(USERS.containsKey(commands[0])) {
			return USERS.get(commands[0]).logout();
		} else {
			return "error:alreadyloggedout";
		}
	}
	
	private String info(String[] commands) {
		if(USERS.containsKey(commands[2])) {
			return USERS.get(commands[2]).info();
		} else {
			return "error:noinfo";
		}
	}
	
	private String listabsent(String[] commands) {
		if(!USERS.containsKey(commands[0])) {
			return "error:notloggedin";
		}
		return this.absentUsers();
	}
	
	private String listavailable(String[] commands) {
		if(!USERS.containsKey(commands[0])) {
			return "error:notloggedin";
		}
		return this.availableUsers();
	}
	
	private String shutdown(Server server) throws IOException {
		server.stopServer();
		return "";
	}
	
	private String absentUsers() {
		String string = "ok";
		for (Entry<String, User> entry : USERS.entrySet()) {
		    if(!entry.getValue().isIn()) 
		    	string += ":" + entry.getKey();
		}
		return string;
	}
	
	private String availableUsers() {
		String string = "ok";
		for (Entry<String, User> entry : USERS.entrySet()) {
		    if(entry.getValue().isIn()) 
		    	string += ":" + entry.getKey();
		}
		return string;
	}
	
}
