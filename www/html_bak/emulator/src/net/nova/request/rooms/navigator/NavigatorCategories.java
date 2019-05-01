package net.nova.request.rooms.navigator;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.VL64;

public class NavigatorCategories implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		session.getServerMessage().send("C]" + VL64.encode(0) + "I");
	}
	
}
