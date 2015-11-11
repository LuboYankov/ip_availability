package org.elsysbg.ip.availability;

public class Server {
	private boolean running;
	private final int port;
	
	public Server(int port) {
		this.port = port;
	}
}
