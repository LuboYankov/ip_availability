package org.elsysbg.ip.availability;

import java.net.Socket;

public interface IUser {
	public Socket getSocket();
	public String login();
	public String logout();
	public String getName();
	public int getLoginCounts();
	public boolean isIn();
	public String info();
	public void addDate();
	public String datesToString();
}
