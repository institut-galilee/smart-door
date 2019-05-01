package net.nova.request.register;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;

public class RegisterDateFormat implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		session.getServerMessage().send("Bc" + (String)(dateFormat.format((new Date()))));
	}

}
