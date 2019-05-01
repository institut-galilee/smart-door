package net.nova.request.login;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.B64;
import net.nova.game.Habbo;
import net.nova.game.Habbos;

public class LoginHandlerSSO implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		int ssoLength = B64.decode(packet.substring(0, 2));
		String sso = packet.substring(2, 2 + ssoLength);
		
		Habbo habbo = Habbos.getBySSO(sso);
		
		if(habbo == null) {
			session.getServerMessage().send("BKsso_no_exists");
			session.close();
			return;
		}
		
		if(habbo.getOnline()) {
			session.getServerMessage().send("BKuser_is_online");
			session.getServerMessage().send("@a");
			session.close();
			return;
		}
		
		// hotel lock
		// @amod_warn/
		
		// baned
		// @c
		
		session.setHabbo(habbo);
		session.getHabbo().setOnline(true);
		session.getHabbo().setLastIp(session.getIp());
		
		// ropas hc
		// @H[...]
		
		session.getServerMessage().send("@B" + Habbos.getFuses(session.getHabbo().getRank()));
		
		session.getServerMessage().send("@C");
	}

}
