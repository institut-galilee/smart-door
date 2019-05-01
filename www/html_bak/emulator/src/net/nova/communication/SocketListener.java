package net.nova.communication;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import net.nova.Log;

public class SocketListener implements Runnable {
	private ServerSocket listener = null;
	private Thread running = null;
	protected SocketListenerManager listenerManager = null;
	
	private int port = -1;
	private int backlog = 0;
	private InetAddress bindIp = null;
	private boolean listen = false;
	
	public SocketListener(int port, int backlog, InetAddress bindIp, SocketListenerManager listenerManager) throws IOException {
		Log.log("Iniciando servidor [" + port + ", " + backlog + ", " + bindIp.toString() + "]");
		listener = new ServerSocket(port, backlog, bindIp);
		Log.logAdd("Listo!");
		
		if(listener == null)
			return;
		
		this.port = port;
		this.backlog = backlog;
		this.bindIp = bindIp;
		this.listenerManager = listenerManager;
		
		listen = true;
		
		running = new Thread(this, "SERVER[" + bindIp.getHostAddress() + ":" + port + "]");
		running.start();
		
		Log.logAddLn("iniciado en el puerto " + port);
	}
	
	public void start() {
		if(running != null)
			running.start();
	}
	
	@Override
	public void run() {
		while(listen) {
			Socket session = null;
			
			try {
				session = listener.accept();
				Log.logLn("Cliente conectado desde: " + session.getInetAddress().getHostAddress());
				
				SocketSession sessionBuffer = listenerManager.createSession();
				if(sessionBuffer != null) {
					sessionBuffer.setSession(session);
				} else {
					session.close();
					Log.logLn("No se aceptan más conexiones");
				}
				
				Thread.currentThread();
				Thread.sleep(15);
			} catch (IOException e) {
				
			} catch (InterruptedException e) {
				
			}
		}
		
		close();
	}
	
	public boolean isConnected() {
		if(listener == null)
			return false;
		
		return !listener.isClosed();
	}
	
	public int getPort() {
		return port;
	}
	
	public void changePort(int port) {
		close();
		
		try {
			listener = new ServerSocket(port, backlog, bindIp);
		} catch (IOException e) {
			
		}
		
		if(listener != null) {
			this.port = port;
			return;
		}
		
		try {
			listener = new ServerSocket(this.port, backlog, bindIp);
		} catch(IOException e) {
			
		}
	}
	
	public int getBacklog() {
		return backlog;
	}
	
	public void changeBacklog(int backlog) {
		close();
		
		try {
			listener = new ServerSocket(port, backlog, bindIp);
		} catch (IOException e) {
			
		}
		
		if(listener != null) {
			this.backlog = backlog;
			return;
		}
		
		try {
			listener = new ServerSocket(port, this.backlog, bindIp);
		} catch(IOException e) {
			
		}
	}
	
	public InetAddress getBindIp() {
		return bindIp;
	}
	
	public void changeBindIp(InetAddress bindIp) {
		close();
		
		try {
			listener = new ServerSocket(port, backlog, bindIp);
		} catch (IOException e) {
			
		}
		
		if(listener != null) {
			this.bindIp = bindIp;
			return;
		}
		
		try {
			listener = new ServerSocket(port, backlog, this.bindIp);
		} catch(IOException e) {
			
		}
	}
	
	public void change(int port, int backlog) {
		close();
		
		try {
			listener = new ServerSocket(port, backlog, bindIp);
		} catch(IOException e) {
			
		}
		
		if(listener != null) {
			this.port = port;
			this.backlog = backlog;
			return;
		}
		
		try {
			listener = new ServerSocket(this.port, this.backlog, bindIp);
		} catch(IOException e) {
			
		}
	}
	
	public void change(int port, int backlog, InetAddress bindIp) {
		close();
		
		try {
			listener = new ServerSocket(port, backlog, bindIp);
		} catch(IOException e) {
			
		}
		
		if(listener != null) {
			this.port = port;
			this.backlog = backlog;
			this.bindIp = bindIp;
			return;
		}
		
		try {
			listener = new ServerSocket(this.port, this.backlog, this.bindIp);
		} catch(IOException e) {
			
		}
	}
	
	public void change(int port, InetAddress bindIp) {
		close();
		
		try {
			listener = new ServerSocket(port, backlog, bindIp);
		} catch(IOException e) {
			
		}
		
		if(listener != null) {
			this.port = port;
			this.bindIp = bindIp;
			return;
		}
		
		try {
			listener = new ServerSocket(this.port, backlog, this.bindIp);
		} catch(IOException e) {
			
		}
	}
	
	public void change(InetAddress bindIp, int backlog) {
		close();
		
		try {
			listener = new ServerSocket(port, backlog, bindIp);
		} catch(IOException e) {
			
		}
		
		if(listener != null) {
			this.bindIp = bindIp;
			this.backlog = backlog;
			return;
		}
		
		try {
			listener = new ServerSocket(port, this.backlog, this.bindIp);
		} catch(IOException e) {
			
		}
	}
	
	public void crash() {
		listen = false;
	}
	
	public void close() {
		if(listener == null)
			return;
		
		if(running != null && !running.isInterrupted()) {
			running.interrupt();
			running = null;
		} else if(running != null) {
			running = null;
		}
		
		listen = false;
		
		if(listener != null) {
			try {
				listener.close();
			} catch (IOException e) {
				
			} finally {
				listener = null;
			}
		}
		
		Log.logLn("Servidor cerrado");
	}
}
