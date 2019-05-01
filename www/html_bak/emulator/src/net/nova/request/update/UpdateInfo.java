package net.nova.request.update;

import net.nova.MD5;
import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.B64;

public class UpdateInfo implements SocketHandlerCallback {

	@Override
	public void setSession(SocketSession session, String packet) {
		String temp = packet;
		int length;
		boolean valid = false;
		
		if(!"".equals(temp) && "@M".equals(temp.substring(0, 2))) {
			temp = temp.substring(2);
			length = B64.decode(temp.substring(0, 2));
			
			String password = MD5.encode(temp.substring(2, 2 + length));
			
			if(password.equals(session.getHabbo().getPassword()))
				valid = true;
			else {
				valid = false;
				session.getServerMessage().send("Bi1");
			}
			
			temp = temp.substring(2 + length);
		}
		
		if(valid && (!"".equals(temp) && "@H".equals(temp.substring(0, 2)))) {
			temp = temp.substring(2);
			length = B64.decode(temp.substring(0, 2));
			
			if(length == 10) {
				String birth = temp.substring(2, 2 + length);
				
				if(birth.equals(session.getHabbo().getBirth()))
					valid = true;
				else {
					valid = false;
					session.getServerMessage().send("Bi2");
				}
			} else {
				valid = false;
				session.getServerMessage().send("Bi2");
			}
			
			temp = temp.substring(2 + length);
		}
		
		if(valid && (!"".equals(temp) && "@C".equals(temp.substring(0, 2)))) {
			temp = temp.substring(2);
			length = B64.decode(temp.substring(0, 2));
			
			String newPassword = temp.substring(2, 2 + length);
			boolean validPassword = newPassword.matches("^.*[a-zA-Z].*$") && newPassword.matches("^.*[0-9].*$");
			
			if(validPassword && (newPassword.length() > 6 && newPassword.length() < 15)) {
				newPassword = MD5.encode(newPassword);
				
				if(!newPassword.equals(session.getHabbo().getPassword()))
					session.getHabbo().setPassword(newPassword);				
				valid = true;
			} else
				valid = false;
			
			temp = temp.substring(2 + length);
		}
		
		if(valid && (!"".equals(temp) && "@G".equals(temp.substring(0, 2)))) {
			temp = temp.substring(2);
			length = B64.decode(temp.substring(0, 2));
			
			String email = temp.substring(2, 2 + length);
			
			if(!email.equals(session.getHabbo().getEmail()))
				session.getHabbo().setEmail(email);				
			valid = true;
			
			temp = temp.substring(2 + length);
		}
		
		if(valid)
			session.getServerMessage().send("Bi0");
		else
			session.getServerMessage().send("BKinfo_update_error");
	}

}
