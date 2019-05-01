/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nova.communication;

import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.nova.Log;
import net.nova.communication.messages.ClientMessage;
import net.nova.communication.messages.ServerMessage;
import net.nova.game.Habbo;

/**
 *
 * @author Sebastian
 */
public class SocketSession implements Runnable {
	private SocketListenerManager listenerManager = null;
	private Socket session = null;
	private Thread running = null;
	
	private ServerMessage output= null;
	private ClientMessage input = null;
	
	private int clientId = -1;
	private boolean listen = false;
	
	private Habbo habbo = null;
	
	public SocketSession(int clientId, SocketListenerManager listenerManager) {
		if(clientId < 0)
			return;
		
		this.clientId = clientId;
		this.listenerManager = listenerManager;
	}
	
	public void setSession(Socket session) {
		if(session.isClosed() || session == null)
			return;
		
		this.session = session;
		
		if(output == null)
			output = new ServerMessage(session, this);
		else
			output.restart(session, this);
		if(input == null)
			input = new ClientMessage(session, this);
		else
			input.restart(session, this);
		
		listen = true;
		
		running = new Thread(this, "CLIENT[" + clientId  + "]");
		running.start();
		
		Log.logLn("Cliente #" + clientId + " iniciado");
	}
	
	@Override
	public void run() {
		if(isClosed())
			return;
		
		output.send("@@");
		Log.logLn("Cliente #" + clientId + " conectado");
		
		while(listen) {
			if(session.isClosed() || input.isClosed() || output.isClosed())
				break;			
			
			try {
				Thread.currentThread();
				Thread.sleep(15);
			} catch (InterruptedException e) {
			}
		}
		
		close();
	}
	
	public boolean socketIsClosed() {
		if(session.isClosed())
			return true;
		
		return false;
	}
	
	public boolean isClosed() {
		if(session == null && listen == false) {
			return true;
		}
		
		return false;
	}
	
	public SocketListenerManager getListenerManager() {
		return listenerManager;
	}
	
	public ServerMessage getServerMessage() {
		return output;
	}
	
	public ClientMessage getClientMessage() {
		return input;
	}
	
	public int getClientId() {
		return clientId;
	}
	
	public void crash() {
		listen = false;
	}
	
	public void close() {
		if(session == null)
			return;
		
		if(running != null && !running.isInterrupted()) {
			running.interrupt();
			running = null;
		} else if(running != null) {
			running = null;
		}
		
		listen = false;
		
		input.close();
		output.close();
		
		if(session != null) {
			try {
				session.close();
			} catch (IOException e) {
				
			} finally {
				session = null;
			}
		}
		
		if(habbo != null) {
			habbo.setSession(null);
			habbo.setLastOnline((String)((new SimpleDateFormat("dd-mm-yyyy HH:ss")).format(new Date())));
			habbo.setOnline(false);
			habbo.destroy();
			habbo = null;
		}
		
		Log.logLn("Cliente #" + clientId + " cerrado");
	}
	
	public String toString() {
		return "Client[" + clientId + "]";
	}

	public Habbo getHabbo() {
		return habbo;
	}

	public void setHabbo(Habbo habbo) {
		habbo.setLastIp(getIp());
		habbo.setSession(this);
		this.habbo = habbo;
	}
	
	public String getIp() {
		if(session == null)
			return "";
		
		return session.getInetAddress().getHostAddress();
	}
}
