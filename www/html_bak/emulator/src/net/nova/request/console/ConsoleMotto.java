package net.nova.request.console;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;

public class ConsoleMotto implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		session.getServerMessage().send("BS" + session.getHabbo().getConsoleMotto());
	}

}
