package org.elsysbg.ip.availability;

import java.io.IOException;

public interface IClientHandler {
	public void run();
	public void stopClient() throws IOException;
	public String[] parseCommand(String string);
	public String checkCommand(String[] commands);
	public String absentUsers();
}
