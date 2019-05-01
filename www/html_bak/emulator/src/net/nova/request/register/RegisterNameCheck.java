package net.nova.request.register;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.B64;
import net.nova.game.Habbos;

public class RegisterNameCheck implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		int nameLength = B64.decode(packet.substring(0, 2));
		String name = packet.substring(2, (2 + nameLength));
		
		if(Habbos.existsHabbo(name))
			session.getServerMessage().send("@dPA");
		else if(name.length() < 4 || name.length() > 15)
			session.getServerMessage().send("@dK");
		else
			session.getServerMessage().send("@dH");
	}
	
}
