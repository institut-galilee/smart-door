package net.nova.request.register;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.B64;

public class RegisterPasswordCheck implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		int nameLength = B64.decode(packet.substring(0, 2));
		String password = packet.substring((4 + nameLength));
		
		boolean valid = password.matches("^.*[a-zA-Z].*$") && password.matches("^.*[0-9].*$");
		
		if(!valid || password.length() < 6 || password.length() > 15)
			session.getServerMessage().send("DZI");
		else
			session.getServerMessage().send("DZH");		
	}
	
}
