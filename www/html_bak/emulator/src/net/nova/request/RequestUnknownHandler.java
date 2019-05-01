package net.nova.request;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;

public class RequestUnknownHandler implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		//session.close();
	}

	public String toString() {
		return "unknown";
	}
	
}
