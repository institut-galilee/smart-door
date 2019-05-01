package net.nova.request.console;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;

public class ConsoleFriendList implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		session.getServerMessage().send("@L" + session.getHabbo().getConsoleMotto() + (char)2);
		//session.getServerMessage().send("BD");
		session.getServerMessage().send("BF0");
	}

}
