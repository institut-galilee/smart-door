package net.nova.request.update;

import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.B64;
import net.nova.crypto.VL64;

public class UpdateData implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		String temp = packet;
		int length;
		
		if(!"".equals(temp) && "@D".equals(temp.substring(0, 2))) {
			temp = temp.substring(2);
			length = B64.decode(temp.substring(0, 2));
			
			if(length == 25) {
				String app = temp.substring(2, 2 + length);
				
				if(!app.equals(session.getHabbo().getApp()))
					session.getHabbo().setApp(app);
			}
			
			temp = temp.substring(2 + length);
		}
		
		if(!"".equals(temp) && "@E".equals(temp.substring(0, 2))) {
			temp = temp.substring(2);
			length = B64.decode(temp.substring(0, 2));
			
			if(length == 1) {
				String gender = temp.substring(2, 2 + length);
				
				if(!gender.equals(session.getHabbo().getGender()))
					session.getHabbo().setGender(gender);
			}
		}
		
		if(!"".equals(temp) && "@F".equals(temp.substring(0, 2))) {
			temp = temp.substring(2);
			length = B64.decode(temp.substring(0, 2));
			
			if(length > 0 && length <= 30) {
				String motto = temp.substring(2, 2 + length);
				
				if(!motto.equals(session.getHabbo().getMotto()))
					session.getHabbo().setMotto(motto);
			}
			
			temp = temp.substring(2 + length);
		}
		
		if(!"".equals(temp) && "@I".equals(temp.substring(0, 2))) {
			temp = temp.substring(2);
			length = B64.decode(temp.substring(0, 2));
			
			boolean receiveEmails = (temp.substring(2, 2 + length) == "@") ? false : true;
			
			if(receiveEmails != (Boolean)(session.getHabbo().getReceiveEmails()))
				session.getHabbo().setReceiveEmails(receiveEmails);
			
			temp = temp.substring(2 + length);
		}
		
		session.getServerMessage().send("@E" + session.getHabbo().getId() + (char)2 + 
				session.getHabbo().getName() + (char)2 + 
				session.getHabbo().getApp() + (char)2 + 
				session.getHabbo().getGender() + (char)2 + 
				session.getHabbo().getMotto() + (char)2 + 
				VL64.encode(session.getHabbo().getTickets()) + 
				session.getHabbo().getFigure() + (char)2 + 
				VL64.encode(session.getHabbo().getFilms()) + "I");
	}

}
