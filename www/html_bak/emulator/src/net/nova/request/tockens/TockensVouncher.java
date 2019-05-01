package net.nova.request.tockens;

import java.util.HashMap;

import net.nova.NovaEmulator;
import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.B64;

public class TockensVouncher implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		int length = B64.decode(packet.substring(0, 2));
		
		if(length == 9) {
			// CT
			// @F + creditos + .0
			// @G + tickets
			String code = packet.substring(2, 2 + length);
			boolean exists = NovaEmulator.getDataQuery().queryRun("SELECT code FROM sys_vounchers WHERE code = '" + code + "' LIMIT 1");
			
			if(exists) {
				HashMap<String, Object> data = NovaEmulator.getDataQuery().queryMapUnique("SELECT amount, type FROM sys_vounchers WHERE code = '" + code + "' LIMIT 1");
				boolean ready = false;
				
				NovaEmulator.getDataQuery().update("DELETE FROM sys_vounchers WHERE code = '" + code + "' LIMIT 1");
				
				if("credits".equals((String) data.get("type"))) {
					session.getHabbo().setCredits(session.getHabbo().getCredits() + ((Integer) data.get("amount")));
					ready = true;
				} else if("tickets".equals((String) data.get("type"))) {
					session.getHabbo().setTickets(session.getHabbo().getTickets() + ((Integer) data.get("amount")));
					ready = true;
				} else
					ready = false;
					
				if(ready) {
					session.getServerMessage().send("CT");
					session.getServerMessage().send("@F" + session.getHabbo().getCredits() + ".0");
					session.getServerMessage().send("@G" + session.getHabbo().getTickets());
				} else
					session.getServerMessage().send("CU2");
			} else
				session.getServerMessage().send("CU1");
		} else 
			session.getServerMessage().send("CU2");
	}

}
