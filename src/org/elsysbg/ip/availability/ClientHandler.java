package org.elsysbg.ip.availability;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
	
	private final Socket socket;
	private static final String COMMAND_STOP_SERVER = "shutdown";
	private final Server Server;
	
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
				out.println(line);
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
	
	public void checkCommand(String[] commands) {
		
	}
	
}
