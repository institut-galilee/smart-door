package net.nova.request.login;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.VL64;

public class LoginGetHabboDataInfo implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		session.getServerMessage().send("@E" + session.getHabbo().getId() + (char)2 + 
				session.getHabbo().getName() + (char)2 + 
				session.getHabbo().getApp() + (char)2 + 
				session.getHabbo().getGender() + (char)2 + 
				session.getHabbo().getMotto() + (char)2 + 
				VL64.encode(session.getHabbo().getTickets()) + 
				session.getHabbo().getFigure() + (char)2 + 
				VL64.encode(session.getHabbo().getFilms()));
	}

}
