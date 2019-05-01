package net.nova.game;

import java.util.HashMap;

import net.nova.NovaEmulator;
import net.nova.communication.SocketSession;

public class Habbo {
	private SocketSession session = null;
	private String fuses;
	
	private long films;
	private long tickets;
	
	private int credits;
	private int id;
	private int hcDays;
	private int rank;
	
	private String app;
	private String birth;
	private String consoleMotto;
	private String email;
	private String figure;
	private String gender;
	private String hcLastUpdate;
	private String lastIp;
	private String lastOnline;
	private String motto;
	private String name;
	private String password;
	//private String sso;
	
	private boolean receiveEmails;
	private boolean online;
	
	public Habbo(HashMap<String, Object> data) {
		films = (data.get("films") != null) ? (Long) data.get("films") : 0;
		tickets = (data.get("tickets") != null) ? (Long) data.get("tickets") : 0;
		
		credits = (data.get("credits") != null) ? (Integer) data.get("credits") : 0;
		id = (data.get("id") != null) ? (Integer) data.get("id") : 0;
		hcDays = (data.get("hc_days") != null) ? (Integer) data.get("hc_days") : 0;
		rank = (data.get("rank") != null) ? (Integer) data.get("rank") : 0;
		
		app = (data.get("app") != null) ? (String) data.get("app") : "";
		birth = (data.get("birth") != null) ? (String) data.get("birth") : "";
		consoleMotto = (data.get("console_motto") != null) ? (String) data.get("console_motto") : "";
		email = (data.get("email") != null) ? (String) data.get("email") : "";
		figure = (data.get("figure") != null) ? (String) data.get("figure") : "";
		gender = (data.get("gender") != null) ? (String) data.get("gender") : "";
		hcLastUpdate = (data.get("hc_last_update") != null) ? (String) data.get("hc_last_update") : "";
		lastIp = (data.get("last_ip") != null) ? (String) data.get("last_ip") : "";
		lastOnline = (data.get("last_online") != null) ? (String) data.get("last_online") : "";
		motto = (data.get("motto") != null) ? (String) data.get("motto") : "";
		name = (data.get("name") != null) ? (String) data.get("name") : "";
		password = (data.get("password") != null) ? (String) data.get("password") : "";
		//sso = (data.get("sso") != null) ? (String) data.get("sso") : "";
		
		receiveEmails = (data.get("receive_emails") != null) ? (Boolean) data.get("receive_emails") : false;
	}
	
	public void destroy() {
		Habbos.destroy(this.id);
		Habbos.destroy(this.name);
	}
	
	public void reload(HashMap<String, Object> data) {
		films = (data.get("films") != null) ? (Long) data.get("films") : 0;
		tickets = (data.get("tickets") != null) ? (Long) data.get("tickets") : 0;
		
		credits = (data.get("credits") != null) ? (Integer) data.get("credits") : 0;
		id = (data.get("id") != null) ? (Integer) data.get("id") : 0;
		hcDays = (data.get("hc_days") != null) ? (Integer) data.get("hc_days") : 0;
		rank = (data.get("rank") != null) ? (Integer) data.get("rank") : 0;
		
		app = (data.get("app") != null) ? (String) data.get("app") : "";
		birth = (data.get("birth") != null) ? (String) data.get("birth") : "";
		consoleMotto = (data.get("console_motto") != null) ? (String) data.get("console_motto") : "";
		email = (data.get("email") != null) ? (String) data.get("email") : "";
		figure = (data.get("figure") != null) ? (String) data.get("figure") : "";
		gender = (data.get("gender") != null) ? (String) data.get("gender") : "";
		hcLastUpdate = (data.get("hc_last_update") != null) ? (String) data.get("hc_last_update") : "";
		lastIp = (data.get("last_ip") != null) ? (String) data.get("last_ip") : "";
		lastOnline = (data.get("last_online") != null) ? (String) data.get("last_online") : "";
		motto = (data.get("motto") != null) ? (String) data.get("motto") : "";
		name = (data.get("name") != null) ? (String) data.get("name") : "";
		password = (data.get("password") != null) ? (String) data.get("password") : "";
		//sso = (data.get("sso") != null) ? (String) data.get("sso") : "";
		
		receiveEmails = (data.get("receive_emails") != null) ? (Boolean) data.get("receive_emails") : false;
	}
	
	public void setSession(SocketSession session) {		
		this.session = session;
	}
	
	public SocketSession getSession() {
		return session;
	}
	
	public void setFuses(String fuses) {
		this.fuses = fuses;
	}
	
	public String getFuses() {
		return fuses;
	}
	
	public void setFilms(long films) {
		this.films = films;
		NovaEmulator.getDataQuery().update("UPDATE sys_users SET films = " + films + " WHERE id = " + id + " LIMIT 1");
	}
	
	public long getFilms() {
		return films;
	}
	
	public void setTickets(long tickets) {
		this.tickets = tickets;
		NovaEmulator.getDataQuery().update("UPDATE sys_users SET tickets = " + tickets + " WHERE id = " + id + " LIMIT 1");
	}
	
	public long getTickets() {
		return tickets;
	}
	
	public void setCredits(int credits) {
		this.credits = credits;
		NovaEmulator.getDataQuery().update("UPDATE sys_users SET credits = " + credits + " WHERE id = " + id + " LIMIT 1");
	}
	
	public int getCredits() {
		return credits;
	}
	
	public int getId() {
		return id;
	}
	
	public int getHcDays() {
		return hcDays;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setApp(String app) {
		this.app = app;
		NovaEmulator.getDataQuery().update("UPDATE sys_users SET app = '" + app + "' WHERE id = " + id + " LIMIT 1");
	}
	
	public String getApp() {
		return app;
	}
	
	public String getBirth() {
		return birth;
	}
	
	public String getConsoleMotto() {
		return consoleMotto;
	}
	
	public void setEmail(String email) {
		this.email = email;
		NovaEmulator.getDataQuery().update("UPDATE sys_users SET email = '" + email + "' WHERE id = " + id + " LIMIT 1");
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getFigure() {
		return figure;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
		NovaEmulator.getDataQuery().update("UPDATE sys_users SET gender = '" + gender + "' WHERE id = " + id + " LIMIT 1");
	}
	
	public String getGender() {
		return gender;
	}
	
	public String getHcLastUpdate() {
		return hcLastUpdate;
	}
	
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
		NovaEmulator.getDataQuery().update("UPDATE sys_users SET last_ip = '" + lastIp + "' WHERE id = " + id + " LIMIT 1");
	}
	
	public String getLastIp() {
		return lastIp;
	}

	public void setLastOnline(String lastOnline) {
		this.lastOnline = lastOnline;
		NovaEmulator.getDataQuery().update("UPDATE sys_users SET last_online = '" + lastOnline + "' WHERE id = " + id + " LIMIT 1");
	}
	
	public String getLastOnline() {
		return lastOnline;
	}
	
	public void setMotto(String motto) {
		this.motto = motto;
		NovaEmulator.getDataQuery().update("UPDATE sys_users SET motto = '" + motto + "' WHERE id = " + id + " LIMIT 1");
	}

	public String getMotto() {
		return motto;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPassword(String password) {
		this.password = password;
		NovaEmulator.getDataQuery().update("UPDATE sys_users SET password = '" + password + "' WHERE id = " + id + " LIMIT 1");
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getSso() {
		return (String) (NovaEmulator.getDataQuery().queryMapUnique("SELECT sso FROM sys_users WHERE id = " + id + " LIMIT 1")).get("sso");
	}
	
	public void setReceiveEmails(boolean receiveEmails) {
		this.receiveEmails = receiveEmails;
		NovaEmulator.getDataQuery().update("UPDATE sys_users SET receive_emails = " + receiveEmails + " WHERE id = " + id + " LIMIT 1");
	}
	
	public boolean getReceiveEmails() {
		return receiveEmails;
	}
	
	public void setOnline(boolean online) {
		this.online = online;
	}
	
	public boolean getOnline() {
		return online;
	}
}
