package net.nova;

public abstract class Log {	
	private static boolean debug = false;
	private static boolean started = false;
	
	public static void start() {		
		started = true;
	}
	
	public static void debug() {
		debug = true;
	}
	
	public static synchronized void packetLn(String log) {
		if(started == false)
			start();
		
		if(!debug)
			return;
		
		//txtLog.setForeground(new java.awt.Color(0, 0, 0));
		System.out.println("[SOCKET] " + log + System.getProperty("line.separator"));
	}
	
	public static synchronized void packetAdd(String log) {
		if(started == false)
			start();
		
		if(!debug)
			return;
		
		//txtLog.setForeground(new java.awt.Color(0, 0, 0));
		System.out.print(" " + log);
	}
	
	public static synchronized void packetAddLn(String log) {
		if(started == false)
			start();
		
		if(!debug)
			return;
		
		//txtLog.setForeground(new java.awt.Color(0, 0, 0));
		System.out.print(" " + log + System.getProperty("line.separator"));
	}
	
	public static synchronized void packet(String log) {
		if(started == false)
			start();
		
		if(!debug)
			return;
		
		//txtLog.setForeground(new java.awt.Color(0, 0, 0));
		System.out.println("[SOCKET] " + log);
	}
	
	public static synchronized void sqlLn(String log) {
		if(started == false)
			start();
		
		if(!debug)
			return;
		
		//txtLog.setForeground(new java.awt.Color(0, 0, 0));
		System.out.println("[SQL] " + log + System.getProperty("line.separator"));
	}
	
	public static synchronized void sqlAdd(String log) {
		if(started == false)
			start();
		
		if(!debug)
			return;
		
		//txtLog.setForeground(new java.awt.Color(0, 0, 0));
		System.out.print(" " + log);
	}
	
	public static synchronized void sqlAddLn(String log) {
		if(started == false)
			start();
		
		if(!debug)
			return;
		
		//txtLog.setForeground(new java.awt.Color(0, 0, 0));
		System.out.print(" " + log + System.getProperty("line.separator"));
	}
	
	public static synchronized void sql(String log) {
		if(started == false)
			start();
		
		if(!debug)
			return;
		
		//txtLog.setForeground(new java.awt.Color(0, 0, 0));
		System.out.println("[SQL] " + log);
	}
	
	public static synchronized void logLn(String log) {
		if(started == false)
			start();
		
		//txtLog.setForeground(new java.awt.Color(0, 0, 0));
		System.out.println("[LOG] " + log + System.getProperty("line.separator"));
	}
	
	public static synchronized void logAdd(String log) {
		if(started == false)
			start();
		
		//txtLog.setForeground(new java.awt.Color(0, 0, 0));
		System.out.print(" " + log);
	}
	
	public static synchronized void logAddLn(String log) {
		if(started == false)
			start();
		
		//txtLog.setForeground(new java.awt.Color(0, 0, 0));
		System.out.print(" " + log + System.getProperty("line.separator"));
	}
	
	public static synchronized void log(String log) {
		if(started == false)
			start();
		
		//txtLog.setForeground(new java.awt.Color(0, 0, 0));
		System.out.println("[LOG] " + log);
	}
	
	public static synchronized void error(String error) {
		if(started == false)
			start();
		
		
		//txtLog.setForeground(new java.awt.Color(255, 0, 0));
		System.out.println("[ERROR] " + error + System.getProperty("line.separator"));
	}
}
