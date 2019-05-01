package net.nova.sqlstorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
	private Connection connection = null;
	private SqlDataQuery dataQuery = null;
	
	private String driver = "";
	private String url = "";
	private String user = "";
	private String password = "";
	
	private boolean actived = false;
	
	public SqlConnection(String driver, String url, String user, String password) throws ClassNotFoundException, SQLException {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
		
		connect();
	}
	
	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		connection = DriverManager.getConnection(url, user, password);
		
		if(connection == null)
			return;
		
		actived = true;
		
		dataQuery = new SqlDataQuery(connection);
	}
	
	public SqlDataQuery getDataQuery() {
		return dataQuery;
	}
	
	@SuppressWarnings("unused")
	public boolean isActived() {
		return actived;
	}
}
