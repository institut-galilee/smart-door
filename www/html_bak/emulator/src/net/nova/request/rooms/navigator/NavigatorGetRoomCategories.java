package net.nova.request.rooms.navigator;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;

public class NavigatorGetRoomCategories implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		session.getServerMessage().send("C\\RRJCategoria" + (char)2 + "IHPAH");
	}

}
