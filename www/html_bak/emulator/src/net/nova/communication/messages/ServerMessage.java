package net.nova.communication.messages;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import net.nova.Log;
import net.nova.communication.SocketSession;

public class ServerMessage {
	private SocketSession session = null;
	private PrintWriter output = null;
	
	public ServerMessage(Socket socket, SocketSession session) {
		if(socket == null || socket.isClosed())
			return;
		
		try {
			output = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			
		}
		
		if(output == null)
			session.close();
		
		this.session = session;
	}
	
	public void restart(Socket socket, SocketSession session) {
		if(socket == null || socket.isClosed())
			return;
		
		try {
			output = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			
		}
		
		if(output == null)
			session.close();
		
		this.session = session;
	}
	
	public void send(String packet) {
		if(output == null)
			return;
		
		Log.packetLn("Cliente #" + session.getClientId() + " << " + packet);
		output.print(packet + (char)1);
		output.flush();
	}
	
	public void close() {
		if(output == null)
			return;
		
		output.close();
		output = null;
	}
	
	public boolean isClosed() {
		if(output == null)
			return true;
		
		return false;
	}
}
