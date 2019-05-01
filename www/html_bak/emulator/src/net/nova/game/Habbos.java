package net.nova.game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.nova.Log;
import net.nova.NovaEmulator;

public class Habbos {
	private static HashMap<Integer, Habbo> habbosById = null;
	private static HashMap<String, Habbo> habbosByName = null;
	
	private static HashMap<Integer, List<String>> fuses = null;
	private static HashMap<Integer, String> fusesCache = null;
	
	private static int habbosCount = 0;
	
	private static boolean ready = false;
	
	public static void init() {
		if(ready == true)
			return;
		
		habbosCount = NovaEmulator.getDataQuery().queryNumRows("SELECT id FROM sys_users");
		habbosById = new HashMap<Integer, Habbo>(habbosCount);
		habbosByName = new HashMap<String, Habbo>(habbosCount);
		if((Boolean) NovaEmulator.getProperty("oldregister"))
			loadHabbos();
		
		int queryRows = NovaEmulator.getDataQuery().queryNumRows("SELECT * FROM sys_fuses");
		if(queryRows <= 0) {
			ready = true;
			return;
		}
		
		fuses = new HashMap<Integer, List<String>>();		
		ResultSet query = NovaEmulator.getDataQuery().query("SELECT * FROM sys_fuses");
		
		try {			
			while(query.next()) {
				int fuseMinRankId = query.getInt(1);
				
				for(;;) {
					if(fuses.containsKey(fuseMinRankId)) {
						fuses.get(fuseMinRankId).add((String) query.getObject(2));
						break;
					} else {
						ArrayList<String> fusesHandlers = new ArrayList<String>();
						fusesHandlers.add((String) query.getObject(2));
						fuses.put((Integer) query.getObject(1), fusesHandlers);
						break;
					}
				}
			}
		} catch (SQLException e) {
		}
		
		if(!fuses.isEmpty())
			fusesCache = new HashMap<Integer, String>();
		
		ready = true;
	}
	
	public static void loadHabbos() {
		if(habbosCount == 0)
			return;
		
		if(habbosById.size() > 0 && habbosByName.size() > 0)
			reloadHabbos();
		
		HashMap<Integer, HashMap<String, Object>> datas = NovaEmulator.getDataQuery().queryMap("SELECT * FROM sys_users");
		
		for(int i = 1; i <= habbosCount; i++) {
			Habbo habbo = new Habbo(datas.get(i));
			
			habbosById.put((Integer) datas.get(i).get("id"), habbo);
			habbosByName.put((String) datas.get(i).get("name"), habbo);
		}
		
		datas.clear();
	}
	
	public static void loadHabbo(int id) {		
		if(!habbosById.containsKey(id)) {
			HashMap<String, Object> data = NovaEmulator.getDataQuery().queryMapUnique("SELECT * FROM sys_users WHERE id = " + id  + " LIMIT 1");
			
			Habbo habbo = new Habbo(data);
			
			habbosById.put(habbo.getId(), habbo);
			habbosByName.put(habbo.getName(), habbo);
			
			habbosCount++;
			
			data.clear();
		} else {
			reloadHabbo(id);
		}
	}
	
	public static void loadHabbo(String name) {
		if(!habbosByName.containsKey(name)) {
			HashMap<String, Object> data = NovaEmulator.getDataQuery().queryMapUnique("SELECT * FROM sys_users WHERE name = '" + name + "' LIMIT 1");
			
			Habbo habbo = new Habbo(data);
			
			habbosById.put(habbo.getId(), habbo);
			habbosByName.put(habbo.getName(), habbo);
			
			habbosCount++;
			
			data.clear();
		} else {
			reloadHabbo(name);
		}
	}
	
	public static Habbo loadHabboBySSO(String sso) {
		HashMap<String, Object> data = NovaEmulator.getDataQuery().queryMapUnique("SELECT * FROM sys_users WHERE sso = '" + sso + "' LIMIT 1");
		
		int id = (Integer) data.get("id");
		String name = (String) data.get("name");
		
		if(!habbosById.containsKey(id))
			loadHabbo(id);
		
		if(!habbosByName.containsKey(name))
			loadHabbo(name);
		
		return habbosById.get(id);
	}
	
	private static void loadHabbo(HashMap<String, Object> data) {		
		Habbo habbo = new Habbo(data);
		
		if(habbo.getId() <= 0 || "".equals(habbo.getName()))
			return;
		
		if(!habbosById.containsKey(habbo.getId()) && !habbosByName.containsKey(habbo.getName())) {
			habbosById.put(habbo.getId(), habbo);
			habbosByName.put(habbo.getName(), habbo);
			
			habbosCount++;
		} else {
			reloadHabbo(data);
		}
		
		data.clear();
	}
	
	public static void reloadHabbos() {
		habbosCount = NovaEmulator.getDataQuery().queryNumRows("SELECT id FROM sys_users");
		habbosById.clear();
		habbosByName.clear();
		
		loadHabbos();
	}
	
	public static void reloadHabbo(int id) {
		if(habbosById.containsKey(id)) {
			String name = habbosById.get(id).getName();
			
			habbosById.remove(id);
			habbosByName.remove(name);
			
			habbosCount--;
			
			loadHabbo(id);
		} else {
			loadHabbo(id);
		}
	}
	
	public static void reloadHabbo(String name) {
		if(habbosByName.containsKey(name)) {
			int id = habbosByName.get(name).getId();
			
			habbosById.remove(id);
			habbosByName.remove(name);
			
			habbosCount--;
			
			loadHabbo(name);
		} else {
			loadHabbo(name);
		}
	}
	
	private static void reloadHabbo(HashMap<String, Object> data) {
		Habbo habbo = new Habbo(data);
		
		if(habbo.getId() <= 0 || "".equals(habbo.getName()))
			return;
		
		if(habbosById.containsKey(habbo.getId()) && habbosById.containsKey(habbo.getName())) {
			habbosById.remove(habbo.getId());
			habbosByName.remove(habbo.getName());
			
			habbosCount--;
		}
		
		habbosById.put(habbo.getId(), habbo);
		habbosByName.put(habbo.getName(), habbo);
		
		habbosCount++;
		
		data.clear();
	}
	
	public static void registerHabbo(HashMap<String, Object> data) {
		if(existsHabbo((String) data.get("name")))
			return;
		
		String lastOnline = (String)((new SimpleDateFormat("dd-mm-yyyy HH:ss")).format(new Date()));
		data.put("last_online", lastOnline);
		
		NovaEmulator.getDataQuery().update("INSERT INTO sys_users(name, app, gender, email, birth, receive_emails, password, sso, rank, last_ip, last_online, figure, films, credits, console_motto, motto, tickets) " + 
		"VALUES('" + (String) data.get("name") + "', '" + (String) data.get("app") + "', '" + (String) data.get("gender") + "', '" + (String) data.get("email") + "', '" + (String) data.get("birth") + "', " + (Boolean) data.get("receive_emails") + ", '" + (String) data.get("password") + "', '', " + (Integer) data.get("rank") + ", '" + (String) data.get("last_ip") + "', '" + lastOnline + "', default, default, default, default, default, default)");
		
		try {
			ResultSet query = NovaEmulator.getDataQuery().query("SELECT MAX(id) FROM sys_users");
			
			if(query.next()) {
				data.put("id", query.getObject(1));
			}			
		} catch (SQLException e) {
		}
		
		if(data.get("id") == null) {
			Log.error("Error al intentar crear un habbo");
			return;
		}
		
		loadHabbo(data);
		data.clear();
	}
	
	public static Habbo getHabbo(int id) {
		if(!existsHabbo(id))
			return null;
		
		return (Habbo) habbosById.get(id);
	}
	
	public static Habbo getHabbo(String name) {
		if(!existsHabbo(name))
			return null;
		
		return (Habbo) habbosByName.get(name);
	}
	
	public static boolean existsHabbo(int id) {
		if((Boolean)NovaEmulator.getProperty("oldregister")) {
			if(habbosById == null || habbosCount == 0 || habbosById.size() <= 0)
				return false;
			
			return habbosById.containsKey(id);
		}
		
		if(NovaEmulator.getDataQuery().queryRun("SELECT id FROM sys_users WHERE id = " + id + " LIMIT 1"))
				return true;
	
		return false;
	}
	
	public static boolean existsHabbo(String name) {
		if((Boolean)NovaEmulator.getProperty("oldregister")) {
			if(habbosByName == null || habbosCount == 0 || habbosByName.size() <= 0)
				return false;
			
			return habbosByName.containsKey(name);
		}
		
		if(NovaEmulator.getDataQuery().queryRun("SELECT name FROM sys_users WHERE name = " + name + " LIMIT 1"))
			return true;

		return false;
	}
	
	public static boolean isHabboOnline(int id) {
		if(!existsHabbo(id))
			return false;
		
		if((Boolean)NovaEmulator.getProperty("oldregister"))
			return ((Habbo) habbosById.get(id)).getOnline();
		else
			return habbosById.containsKey(id);
	}
	
	public static boolean isHabboOnline(String name) {
		if(!existsHabbo(name))
			return false;

		if((Boolean)NovaEmulator.getProperty("oldregister"))
			return ((Habbo) habbosByName.get(name)).getOnline();
		else
			return habbosByName.containsKey(name);
	}
	
	public static String getFuses(int fuseMinRankId) {
		if(fuses == null || fuses.size() <= 0 || !fuses.containsKey(fuseMinRankId))
			return "";
		
		if(!fusesCache.isEmpty() && fusesCache.containsKey(fuseMinRankId)) {
			return (String) fusesCache.get(fuseMinRankId);
		}
		
		StringBuilder fusesString = new StringBuilder();
		int fusesSize = fuses.get(fuseMinRankId).size()-1;
		
		for(int i = 0; i <= fusesSize; i++) {
			if(i < fusesSize) {
				fusesString.append(fuses.get(fuseMinRankId).get(i)).append((char) 2);
			} else {
				fusesString.append(fuses.get(fuseMinRankId).get(i));
			}
		}
		
		if(fusesCache.isEmpty() || "".equals((String) fusesCache.get(fuseMinRankId)) || (Object) fusesCache.get(fuseMinRankId) == null) {
			fusesCache.put(fuseMinRankId, fusesString.toString());
		}
		
		return fusesString.toString();
	}
	
	public static boolean existsSSO(String sso) {
		if((Boolean)NovaEmulator.getProperty("oldregister")) {
			if(habbosById == null || habbosCount == 0 || habbosById.size() <= 0)
				return false;
			
			for(int i = 1; i <= habbosCount; i++) {
				if(((Habbo) habbosById.get(i)) != null && sso.equals(((Habbo) habbosById.get(i)).getSso())) {
					return true;
				}
			}
		}
		
		if(NovaEmulator.getDataQuery().queryRun("SELECT sso FROM sys_users WHERE sso = " + sso + " LIMIT 1"))
			return true;
		
		return false;
	}
	
	public static Habbo getBySSO(String sso) {
		if(!existsSSO(sso))
			return null;
		
		if((Boolean)NovaEmulator.getProperty("oldregister")) {
			for(int i = 1; i <= habbosCount; i++) {
				if(((Habbo) habbosById.get(i)) != null && sso.equals(((Habbo) habbosById.get(i)).getSso())) {
					return (Habbo) habbosById.get(i);
				}
			}
		} else
			return loadHabboBySSO(sso);
		
		return null;
	}
	
	public static void destroy(int id) {
		if(!habbosById.containsKey(id))
			return;
		
		String name = habbosById.get(id).getName();
		
		habbosById.remove(id);
		habbosByName.remove(name);
	}
	
	public static void destroy(String name) {
		if(!habbosByName.containsKey(name))
			return;
		
		int id = habbosByName.get(name).getId();
		
		habbosById.remove(id);
		habbosByName.remove(name);
	}
	
	public static List<Habbo> getHabbosByPattern(String name) {
		ResultSet results = NovaEmulator.getDataQuery().query("SELECT name FROM sys_users WHERE name LIKE '%" + name + "%'");
		List<Habbo> habbos = new ArrayList<Habbo>();
		
		try {
			while(results.next()) {
				Habbo habbo = getHabbo(results.getString("name"));
				habbos.add(habbo);
			}
			
			results.close();
		} catch (SQLException e) {
		}
		
		return habbos;
	}
}
