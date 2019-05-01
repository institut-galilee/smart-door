package net.nova;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.nova.communication.SocketListener;
import net.nova.communication.SocketListenerManager;
import net.nova.game.Habbos;
import net.nova.request.RequestOnlineUser;
import net.nova.request.RequestGetServerData;
import net.nova.request.RequestEmptyHandler;
import net.nova.request.RequestConnection;
import net.nova.request.console.ConsoleFriendList;
import net.nova.request.console.ConsoleFriendshipRequest;
import net.nova.request.console.ConsoleMotto;
import net.nova.request.console.ConsoleSearchUser;
import net.nova.request.info.InfoGetAvailableBadges;
import net.nova.request.login.LoginGetHabboDataInfo;
import net.nova.request.login.LoginHandler;
import net.nova.request.login.LoginHandlerSSO;
import net.nova.request.register.RegisterBirthCheck;
import net.nova.request.register.RegisterSecondBirthCheck;
import net.nova.request.register.RegisterDateFormat;
import net.nova.request.register.RegisterEmailCheck;
import net.nova.request.register.RegisterNameCheck;
import net.nova.request.register.RegisterPasswordCheck;
import net.nova.request.register.RegisterHandler;
import net.nova.request.rooms.navigator.NavigatorGetRoomCategories;
import net.nova.request.rooms.navigator.NavigatorCategories;
import net.nova.request.rooms.navigator.NavigatorGetRecommendedRooms;
import net.nova.request.tockens.TockensGetClubStatus;
import net.nova.request.tockens.TockensGetCredits;
import net.nova.request.tockens.TockensPurseClub;
import net.nova.request.tockens.TockensVouncher;
import net.nova.request.update.UpdateInfo;
import net.nova.request.update.UpdateData;
import net.nova.sqlstorage.SqlConnection;
import net.nova.sqlstorage.SqlDataQuery;

/**
 *
 * @author Sebastian
 */
public final class NovaEmulator {
	private SocketListenerManager listenerManager = null;
	private SocketListener listener = null;
	private static SqlConnection connection = null;
	
	private static HashMap<String, Object> hotelProperties = new HashMap<String, Object>();
	
	public NovaEmulator() {				
		Log.debug();
		
		boolean error = false;
		List<String> errorList = new ArrayList<String>();
		
		while(!error) {	
			try {
				Config.init();
			} catch(FileNotFoundException e1) {
				error = true;
				errorList.add(System.getProperty("user.dir") + System.getProperty("file.separator") + "config.properties");
			} catch(IOException e2) {
				error = true;
				errorList.add("No se puede leer el archivo");
			}
			
			if(error == true)
				break;
			
			try {
				connection = new SqlConnection((String) Config.getProperty("mysql.driver"), (String) Config.getProperty("mysql.url"), (String) Config.getProperty("mysql.user"), (String) Config.getProperty("mysql.password"));
			} catch(ClassNotFoundException e3) {
				error = true;
				errorList.add("La libreria mysql no fue encontrada");
			} catch(SQLException e4) {
				error = true;
				errorList.add("Existe un error en la conexión");
			}
			
			if(error == true)
				break;
			Habbos.init();
			
			listenerManager = new SocketListenerManager(100);
			registerHandlers();
			
			try {
					listener = new SocketListener(Integer.parseInt((String) Config.getProperty("net.port")), Integer.parseInt((String) Config.getProperty("net.backlog")), InetAddress.getByName((String) Config.getProperty("net.address")), listenerManager);
			} catch (NumberFormatException e5) {
				error = true;
				errorList.add("Error al iniciar el servidor");
			} catch (IOException e6) {
				error = true;
				errorList.add("Error al iniciar el servidor");
			}
			
			break;
		}
		
		hotelProperties.put("oldregister", (Boolean) Config.getProperty("hotel.oldregister", false));
		
		if(error == true) {
			Log.log("Al parecer existen errores, a continuación una lista deatallada:");
			while(errorList.size() > 0) {
				Log.log("- " + errorList.get(0));
				errorList.remove(0);
			}
		} else
			Log.log("Nova iniciado con exito");		
	}
	
	public static Object getProperty(String key) {
		return hotelProperties.get(key);
	}
	
	public static SqlDataQuery getDataQuery() {
		return connection.getDataQuery();
	}

	public void registerHandlers() {
		// Paquetes generales para cualquier conexiï¿½n
		listenerManager.registerHandlerCallback(196, new RequestOnlineUser());
		listenerManager.registerHandlerCallback(202, new RequestGetServerData());
		listenerManager.registerHandlerCallback(206, new RequestConnection());
		
		// Paquetes del registro
		listenerManager.registerHandlerCallback(42, new RegisterNameCheck());
		listenerManager.registerHandlerCallback(43, new RegisterHandler());
		listenerManager.registerHandlerCallback(46, new RegisterSecondBirthCheck());
		listenerManager.registerHandlerCallback(49, new RegisterDateFormat());
		listenerManager.registerHandlerCallback(146, new RegisterBirthCheck());
		listenerManager.registerHandlerCallback(147, new RegisterEmailCheck());
		listenerManager.registerHandlerCallback(197, new RegisterEmailCheck());
		listenerManager.registerHandlerCallback(203, new RegisterPasswordCheck());
		
		// Paquetes del login
		listenerManager.registerHandlerCallback(4, new LoginHandler());
		listenerManager.registerHandlerCallback(7, new LoginGetHabboDataInfo());
		listenerManager.registerHandlerCallback(8, new TockensGetCredits());
		listenerManager.registerHandlerCallback(26, new TockensGetClubStatus());
		listenerManager.registerHandlerCallback(204, new LoginHandlerSSO());
		
		// Paquetes de consola
		listenerManager.registerHandlerCallback(12, new ConsoleFriendList());
		listenerManager.registerHandlerCallback(36, new ConsoleMotto());
		listenerManager.registerHandlerCallback(39, new ConsoleFriendshipRequest());
		listenerManager.registerHandlerCallback(41, new ConsoleSearchUser());
		
		// Paquetes de actualizaciÃ³n
		listenerManager.registerHandlerCallback(44, new UpdateData());
		listenerManager.registerHandlerCallback(149, new UpdateInfo());
		
		// Paquetes de manejo de fichas
		listenerManager.registerHandlerCallback(129, new TockensVouncher());
		
		// Paquetes del habbo club
		listenerManager.registerHandlerCallback(190, new TockensPurseClub());
		
		// Paquetes de placas
		listenerManager.registerHandlerCallback(157, new InfoGetAvailableBadges());
		
		// Paquetes del navegador de salas
		listenerManager.registerHandlerCallback(150, new NavigatorGetRoomCategories());
		listenerManager.registerHandlerCallback(151, new NavigatorCategories());
		listenerManager.registerHandlerCallback(264, new NavigatorGetRecommendedRooms());		
		
		// Handlers vacios
		//listenerManager.registerHandlerCallback(157, new EmptyHandler());
		listenerManager.registerHandlerCallback(228, new RequestEmptyHandler());
		listenerManager.registerHandlerCallback(222, new RequestEmptyHandler());
		listenerManager.registerHandlerCallback(223, new RequestEmptyHandler());
	}
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new NovaEmulator();
			}
		});
	}
}
