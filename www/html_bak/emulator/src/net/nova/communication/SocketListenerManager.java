package net.nova.communication;

import java.util.ArrayList;

import net.nova.Log;
import net.nova.crypto.B64;
import net.nova.request.RequestUnknownHandler;

public class SocketListenerManager {
	private SocketHandlerCallback[] handlers = new SocketHandlerCallback[1024];
	private ArrayList<SocketSession> clients = null;
	private int defaultClientsConnection = 100;
	
	public SocketListenerManager(int clientsConnection) {
		if(clientsConnection > 0)
			defaultClientsConnection = clientsConnection;
		clients = new ArrayList<SocketSession>(defaultClientsConnection);
		handlers[0] = new RequestUnknownHandler();
	}
	
	public SocketSession createSession() {		
		SocketSession sessionReturned = null;
		int clientId = 1;
		
		for(SocketSession client : clients) {			
			if(client != null && (client.isClosed() || client.socketIsClosed())) {
				client.close();
				sessionReturned = client;
				break;
			}
			
			clientId++;
		}
		
		if(sessionReturned == null && clientId <= defaultClientsConnection) {
			clients.add(clientId - 1, new SocketSession(clientId, this));
			sessionReturned = (SocketSession) clients.get(clientId - 1);
		}
		
		return sessionReturned;
	}
	
	public void freeSession(int clientId) {
		
	}
	
	public boolean sessionIsActived(int connectionId) {
		return false;
	}
	
	public int getSessions() {
		return 0;
	}
	
	public int getSessionsActived() {
		return 0;
	}
	
	public void registerHandlerCallback(int packetId, SocketHandlerCallback response) {
		if(handlers[packetId] != null)
			return;
		
		handlers[packetId] = response;
	}
	
	public SocketHandlerCallback getHandlerCallback(int packetId) {
		if(handlers[packetId] == null)
			return handlers[0];
		
		return handlers[packetId];
	}
	
	public void processPacket(String packet, SocketSession session) {
		while(true)
		{
			int packetLength = B64.decode(packet.substring(1, 3));
			if(packetLength < 0 || packetLength > 1024)
				break;
			
			int packetId = B64.decode(packet.substring(3, 5));
			String packetProcess = (packetLength <= 2 ? "" : packet.substring(5, 3 + packetLength));
			
			SocketHandlerCallback handlerCallback = getHandlerCallback(packetId);
			if(handlerCallback == null) {
				Log.error("Paquete no registrado: " + packetProcess + " - " + packetId);
				//session.close();
				break;
			} else if(handlerCallback.toString() == "unknown") {
				Log.error("Paquete no codeado: " + packetProcess + " - " + packetId);
			}
			handlerCallback.setSession(session, packetProcess);
			
			if(packet.length() > (3 + packetLength))
				packet = packet.substring(3 + packetLength);
			else
				break;
		}
		
		return;
	}
}
