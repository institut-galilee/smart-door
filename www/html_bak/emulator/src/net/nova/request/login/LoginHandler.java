package net.nova.request.login;

import net.nova.MD5;
import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.B64;
import net.nova.game.Habbos;

public class LoginHandler implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		int nameLength = B64.decode(packet.substring(0, 2));
		String name = packet.substring(2, 2 + nameLength);
		
		String password = MD5.encode(packet.substring(4 + nameLength, packet.length()));
		
		// errores
		// @a
		if(!Habbos.existsHabbo(name)) {
			//session.getServerMessage().send("BKEl usuario no existe.");
			session.getServerMessage().send("@alogin incorrect: Wrong username");
			session.close();
			return;
		}else if(Habbos.getHabbo(name) != null && !password.equals((Habbos.getHabbo(name)).getPassword())) {
			//session.getServerMessage().send("BKContrase&ntilde;a incorrecta.");
			session.getServerMessage().send("@alogin incorrect: Wrong password");
			session.close();
			return;
		}
		
		if(Habbos.isHabboOnline(name)) {			
			session.getServerMessage().send("BKuser_is_online");
			session.getServerMessage().send("@a");
			session.close();
			return;
		}
		
		// hotel lock
		// @amod_warn/
		
		// baned
		// @c
		
		session.setHabbo(Habbos.getHabbo(name));
		session.getHabbo().setOnline(true);		
		session.getHabbo().setLastIp(session.getIp());
		
		// ropas hc
		// @H[...]
		
		// fuses
		// @B + fuses
		//HashMap<Integer, HashMap<String, Object>> fuses = NovaEmulator.getDataQuery().queryMap("");
		session.getServerMessage().send("@B" + Habbos.getFuses(session.getHabbo().getRank()));
		
		// films vl64
		// @D + vl64encode(films)
		// session.getServerMessage().send("@D" + VL64.encode(session.getHabbo().getFilms()));
		
		// desconocido
		// @C
		session.getServerMessage().send("@C");
	}

}
