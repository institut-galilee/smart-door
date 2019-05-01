package net.nova.request.tockens;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
//import net.nova.crypto.VL64;
//import net.nova.game.Habbo;

public class TockensGetCredits implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		//StringBuilder packetBuffer = new StringBuilder();
		//Habbo habbo = session.getHabbo();
		
		/*if(!"".equals(habbo.getConsoleMotto()))
			packetBuffer.append(habbo.getConsoleMotto()).append((char)2);
		else
			packetBuffer.append("\u00bfEn qu\u00e9 piensas hoy?").append((char)2);*/
		
		/*packetBuffer.append("BI").append(VL64.encode(habbo.getId())).append(habbo.getName()).append((char)2);
		
		if("M".equals(habbo.getGender()))
			packetBuffer.append("I");
		else
			packetBuffer.append("H");
		
		if(!"".equals(habbo.getConsoleMotto()))
			packetBuffer.append("I");
		else
			packetBuffer.append("H");
		
		// User position
		packetBuffer.append((char)2).append("Ion hotel view").append((char)2);
		
		packetBuffer.append(habbo.getLastOnline()).append((char)2)
		.append(habbo.getApp()).append((char)2)
		.append((char)1);*/
		
		//session.getServerMessage().send("@L" + packetBuffer.toString());
		
		//session.getServerMessage().send("@r");
		/*session.getServerMessage().send("@E" + habbo.getId() + (char)2 + 
				habbo.getName() + (char)2 + 
				habbo.getApp() + (char)2 + 
				habbo.getGender() + (char)2 + 
				habbo.getMotto() + (char)2 + 
				VL64.encode(habbo.getTickets()) + 
				habbo.getFigure() + (char)2 + 
				VL64.encode(habbo.getFilms()) + "I");*/
		
		session.getServerMessage().send("@F" + session.getHabbo().getCredits() + ".0");
		
		// Categorias
		// C] VL64.encode(listCount) list
		//session.getServerMessage().send("C]" + VL64.encode(0) + "I");
	}

}
