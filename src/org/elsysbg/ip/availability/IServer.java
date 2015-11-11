package org.elsysbg.ip.availability;

import java.io.IOException;

public interface IServer {
	public void startServer() throws IOException;
	public void stopServer() throws IOException;
	public boolean isRunning();
	public void onClientStopped(ClientHandler clientHandler);
}
