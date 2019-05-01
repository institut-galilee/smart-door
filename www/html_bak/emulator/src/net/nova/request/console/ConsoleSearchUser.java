package net.nova.request.console;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.B64;
import net.nova.crypto.VL64;
import net.nova.game.Habbo;
import net.nova.game.Habbos;

public class ConsoleSearchUser implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		String temp = packet;
		
		int nameLength = B64.decode(temp.substring(0, 2));
		temp = temp.substring(2);
		String name = temp.substring(0, nameLength);
		temp = temp.substring(nameLength);
		
		int destinationLength = B64.decode(temp.substring(0, 2));
		temp = temp.substring(2);
		String destination = temp.substring(0, destinationLength);
		
		StringBuilder packetBuffer = new StringBuilder();
		packetBuffer.append("B@" + destination).append((char)2);

		Habbo habbo = Habbos.getHabbo(name);
		if(habbo != null) {
			packetBuffer.append(VL64.encode(habbo.getId()));
			packetBuffer.append(habbo.getName()).append((char)2);
			packetBuffer.append(VL64.encode(("M".equals(habbo.getGender())) ? 1 : 0));
			packetBuffer.append(habbo.getConsoleMotto()).append((char)2);
			packetBuffer.append(VL64.encode((habbo.getOnline()) ? 1 : 0));
			packetBuffer.append("Floor1b").append((char)2);
			packetBuffer.append(habbo.getLastOnline()).append((char)2);
			packetBuffer.append(habbo.getApp()).append((char)2);
		} else
			packetBuffer.append(VL64.encode(0));
		
		session.getServerMessage().send(packetBuffer.toString());
	}

}
