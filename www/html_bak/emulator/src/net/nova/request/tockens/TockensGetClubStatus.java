package net.nova.request.tockens;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.VL64;

public class TockensGetClubStatus implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		// @Gclub_habbo(char)2 VL64.encode(dias restantes) VL64.encode(meses pasados) VL64.encode(meses restantes) H 
		StringBuilder packetBuffer = new StringBuilder();
		
		// club
		packetBuffer.append("club_habbo").append((char)2);
		// days remaining
		packetBuffer.append(VL64.encode(0));
		// periods passed
		packetBuffer.append(VL64.encode(0));
		// periods reaming
		packetBuffer.append(VL64.encode(0));
		// boolean true or false
		packetBuffer.append(VL64.encode(1));
		
		session.getServerMessage().send("@G" + packetBuffer.toString());
		
		session.getServerMessage().send("BKprueba_text");
	}

}
