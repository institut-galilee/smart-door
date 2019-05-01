package net.nova.communication;

public interface SocketHandlerCallback {
	public void setSession(SocketSession session, String packet);
}
