package net.nova.request.console;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.VL64;

public class ConsoleFriendshipAccept implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		String temp = packet;
		int idLength = 0;
		
		if(!"".equals(temp)) {
			idLength = VL64.decode(temp);
			int currentOffset = VL64.encode(idLength).length();
			temp = temp.substring(currentOffset);
		}
		
		if(idLength == 0)
			return;
		
		int id = VL64.decode(temp.substring(0, idLength));
	}

}
