package net.nova.request.rooms.navigator;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.VL64;

public class NavigatorGetRecommendedRooms implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		session.getServerMessage().send("E_" + VL64.encode(0));
	}

}
