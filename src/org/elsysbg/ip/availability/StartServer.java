package org.elsysbg.ip.availability;

public class StartServer {

	private static final int SERVER_PORT = 31111;
	
	public static void main(String[] args) {
		final Server server = new Server(SERVER_PORT);
		server.startServer();
	}

}
