package net.nova.communication.messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import net.nova.Log;
import net.nova.communication.SocketSession;

public class ClientMessage implements Runnable {
	private SocketSession session = null;
	private BufferedReader input = null;
	private Thread running = null;
	
	private boolean listen = false;
	
	public ClientMessage(Socket socket, SocketSession session) {
		if(socket == null || socket.isClosed())
			return;
		
		try {
			input = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));
		} catch (IOException e) {
			
		}
		
		if(input == null)
			session.close();
		
		this.session = session;
		
		listen = true;
		
		running = new Thread(this, "INPUT[" + session.getClientId() + "]");
		running.start();
	}
	
	public void restart(Socket socket, SocketSession session) {
		if(socket == null || socket.isClosed())
			return;
		try {
			input = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));
		} catch (IOException e) {
		}
		
		if(input == null)
			session.close();
		
		this.session = session;
		
		listen = true;
		
		running = new Thread(this, "INPUT[" + session.getClientId() + "]");
		running.start();
	}

	public void run() {		
		while(listen) {
			try {
				String packet = "";
				{
					char[] msg = new char[512];
					int length = input.read(msg);
					for(int i = 0; i < length; i++)
						packet = packet + (char)(int)msg[i];
				}
				
				if(packet == "" || packet.length() == 0 || packet.equals((char)10 + (char)13 + "")) {
					break;
				}
				
				Log.packetLn("Cliente #" + session.getClientId() + " >> " + packet);
				session.getListenerManager().processPacket(packet, session);
			
				Thread.currentThread();
				Thread.sleep(15);
			} catch (IOException e) {
				break;
			} catch (InterruptedException e) {
				break;
			}
		}
		
		this.close();
	}
	
	public void close() {
		if(!listen && input == null)
			return;
		
		if(running != null && !running.isInterrupted()) {
			running.interrupt();
			running = null;
		} else if(running != null) {
			running = null;
		}
		
		listen = false;
		
		try {
			input.close();
		} catch (IOException e) {
			
		} finally {
			input = null;
		}
	}
	
	public boolean isClosed() {
		if(!listen || input == null || running == null) {
			return true;
		}
		
		return false;
	}
}
