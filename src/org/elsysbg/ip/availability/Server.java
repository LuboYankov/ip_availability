package org.elsysbg.ip.availability;

public class Server {
	private boolean running;
	private final int port;
	
	public Server(int port) {
		this.port = port;
	}
	
	public void startServer() {
		setRunning();
	}
	
	public boolean isRunning() {
		return running;
	}
	
	private void setRunning() {
		if(running) {
			throw new IllegalStateException("Already running");
		}
		running = true;
	}
}
