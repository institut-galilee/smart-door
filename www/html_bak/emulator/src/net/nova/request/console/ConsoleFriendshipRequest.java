package net.nova.request.console;

import net.nova.NovaEmulator;
import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.B64;
import net.nova.crypto.VL64;
import net.nova.game.Habbo;
import net.nova.game.Habbos;

public class ConsoleFriendshipRequest implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		int nameLength = B64.decode(packet.substring(0, 2));
		String name = packet.substring(2, 2 + nameLength);
		
		if(Habbos.existsHabbo(name)) {
			Habbo habbo = Habbos.getHabbo(name);
			NovaEmulator.getDataQuery().update("INSERT INTO sys_friendship(user_one_id, user_two_id) VALUES(" + session.getHabbo().getId() + ", " + habbo.getId() + ") LIMIT 1");
			
			if(habbo.getOnline()) {
				habbo.getSession().getServerMessage().send("BD" +
															VL64.encode(session.getHabbo().getId()) +
															session.getHabbo().getName() + (char)2);
			}
		}
		
		session.getServerMessage().send("BD" + VL64.encode(3) + session.getHabbo().getName() + (char)2);
	}

}
