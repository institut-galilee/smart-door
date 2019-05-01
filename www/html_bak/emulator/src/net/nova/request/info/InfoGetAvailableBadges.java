package net.nova.request.info;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.VL64;

public class InfoGetAvailableBadges implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		// Ce Vl64(#Placas) NombPlaca(char)2 VL64(PlacaActual) Vl64(activo/desactivo)
		
		StringBuilder packetBuffer = new StringBuilder();
		
		// badges count
		packetBuffer.append(VL64.encode(0));
		// badge id
		//packetBuffer.append(VL64.encode(0)).append((char)2);
		// current badge order
		packetBuffer.append(VL64.encode(0));
		// badge visible
		packetBuffer.append(VL64.encode(0));
		
		session.getServerMessage().send("Ce" + packetBuffer.toString());
	}

}
