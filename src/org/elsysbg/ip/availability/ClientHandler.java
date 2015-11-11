package org.elsysbg.ip.availability;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ClientHandler implements Runnable {
	
	private final Socket socket;
	private static final String COMMAND_STOP_SERVER = "shutdown";
	private final Server Server;
	private Map<String, User> USERS = new HashMap<String, User>();
	
	public ClientHandler(Socket socket, Server Server) {
		this.socket = socket;
		this.Server = Server;
	}
	
	@Override
	public void run() {
		try {
			final PrintStream out = new PrintStream(socket.getOutputStream());
			final Scanner scanner = new Scanner(socket.getInputStream());
			while(scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if(COMMAND_STOP_SERVER.equals(line)) {
					Server.stopServer();
					break;
				}
				out.println(checkCommand(parseCommand(line)));
			}
			scanner.close();
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			Server.onClientStopped(this);
		}
	}
	
	public void stopClient() throws IOException {
		socket.close();
	}
	
	public String[] parseCommand(String string) {
		String[] commands = string.split(":");
		return commands;
	}
	
	public String checkCommand(String[] commands) {
		switch(commands[1]) {
			case "login": 
				if(!USERS.containsKey(commands[0])) {
					USERS.put(commands[0], new User(commands[0], socket));
				}
				return USERS.get(commands[0]).login();
			case "logout":
				if(USERS.containsKey(commands[0])) {
					return USERS.get(commands[0]).logout();
				}
				break;
			case "info": // user info
				break;
			case "listabsent": // listing users that are not logged in
				break;
			default:
				return "Unknown command - " + commands[1];
		}
		return null;
	}
	
}
