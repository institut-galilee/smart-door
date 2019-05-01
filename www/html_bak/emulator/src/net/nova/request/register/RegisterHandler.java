package net.nova.request.register;

import java.util.HashMap;

import net.nova.MD5;
import net.nova.communication.SocketHandlerCallback;
import net.nova.communication.SocketSession;
import net.nova.crypto.B64;
import net.nova.game.Habbos;

public class RegisterHandler implements SocketHandlerCallback {
	@Override
	public void setSession(SocketSession session, String packet) {
		String temp = packet;
		
		int nameLength = B64.decode(temp.substring(2, 4));
		String name = temp.substring(4, (4 + nameLength));				
		temp = temp.substring(4 + nameLength);
		
		int appLength = B64.decode(temp.substring(2, 4));
		String app = temp.substring(4, (4 + appLength));
		temp = temp.substring(4 + appLength);
		
		String gender = (!"M".equals(Character.toString(temp.charAt(4))) && !"F".equals(Character.toString(temp.charAt(4)))) ? "" : Character.toString(temp.charAt(4));
		temp = temp.substring(9);
		
		int emailLength = B64.decode(temp.substring(2, 4));
		String email = temp.substring(4, (4 + emailLength));
		temp = temp.substring(4 + emailLength);
		
		int birthLength = B64.decode(temp.substring(2, 4));
		String birth = temp.substring(4, (4 + birthLength));
		temp = temp.substring(4 + birthLength);
		
		boolean receiveEmails = (temp.charAt(8) == 'A');
		temp = temp.substring(9);
		
		int passwordLength = B64.decode(temp.substring(2, 4));
		String password = temp.substring(4, 4 + passwordLength);
		
		if(Habbos.existsHabbo(name) || (name.length() < 4 || name.length() > 15) || app.length() != 25 || birth.length() != 10 || (password.length() < 6 || password.length() > 10) || !(password.matches("^.*[a-zA-Z].*$") && password.matches("^.*[0-9].*$")) || "".equals(gender)) {
			session.close();
			return;
		}
		
		password = MD5.encode(password);
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name", name);
		data.put("app", app);
		data.put("gender", gender);
		data.put("email", email);
		data.put("birth", birth);
		data.put("receive_emails", receiveEmails);
		data.put("password", password);
		data.put("rank", 1);
		data.put("last_ip", session.getIp());
		
		Habbos.registerHabbo(data);
		/*("INSERT INTO sys_users(
		 * name
		 * , app
		 * , gender
		 * , email,
		 *  birth, 
		 *  recive_emails, 
		 *  password) " + "VALUES('" + name + "', '" + app + "', '" + gender + "', '" + email + "', '" + birth + "', " +	reciveEmails + ", '" + password + "')");
		*/
	}
}
