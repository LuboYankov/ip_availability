package org.elsysbg.ip.availability;

import java.io.IOException;

public interface IClientHandler {
	public void run();
	public void stopClient() throws IOException;
}
