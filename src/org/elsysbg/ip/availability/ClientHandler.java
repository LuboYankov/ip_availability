package org.elsysbg.ip.availability;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable, IClientHandler {
	
	private final Socket socket;
	private final Server Server;
	private final CommandHandler commandHandler;
	private final static String SHUTDOWN_COMMAND = "shutdown";
	
	public ClientHandler(Socket socket, Server Server) {
		this.socket = socket;
		this.Server = Server;
		this.commandHandler = new CommandHandler(socket);
	}
	
	@Override
	public void run() {
		try {
			final PrintStream out = new PrintStream(socket.getOutputStream());
			final Scanner scanner = new Scanner(socket.getInputStream());
			while(scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				out.println(commandHandler.checkCommand(commandHandler.parseCommand(line), Server));
				if(SHUTDOWN_COMMAND.equals(commandHandler.parseCommand(line)[1])) {
					break;
				}
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
}
