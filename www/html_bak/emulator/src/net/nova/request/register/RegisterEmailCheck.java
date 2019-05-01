package net.nova.request.register;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;

public class RegisterEmailCheck implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		session.getServerMessage().send("DO");
	}

}
