package org.elsysbg.ip.availability;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Server implements IServer {
	private boolean running;
	private final int port;
	private final List<ClientHandler> clients = Collections.synchronizedList(new LinkedList<ClientHandler>());
	
	public Server(int port) {
		this.port = port;
	}
	
	public void startServer() throws IOException {
		setRunning();
		final ServerSocket serverSocket = new ServerSocket(port);
		while(isRunning()) {
			final Socket socket = serverSocket.accept();
			final ClientHandler client = new ClientHandler(socket, this);
			new Thread(client).start();
		}
		serverSocket.close();
	}
	
	public synchronized void stopServer() throws IOException {
		running = false;
		for(ClientHandler next : clients) {
			next.stopClient();
		}
	}
	
	public synchronized boolean isRunning() {
		return running;
	}
	
	private void setRunning() {
		if(running) {
			throw new IllegalStateException("Already running");
		}
		running = true;
	}
	
	public synchronized void onClientStopped(ClientHandler clientHandler) {
		clients.remove(clientHandler);
	}
	
}
