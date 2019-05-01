package net.nova.sqlstorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.nova.Log;

public class SqlDataQuery {
	private PreparedStatement statement = null;
	private Connection connection = null;
	
	public SqlDataQuery(Connection connection) {
		this.connection = connection;
	}
	
	public void update(String query) {		
		try {
			statement = connection.prepareStatement(query + ";");
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			Log.sqlLn("La consulta: " + query + " tipo \"update\" no se pudo ejecutar");
		}
		
		statement = null;
	}
	
	public boolean queryRun(String query) {
		int rows = queryRows(query);
		boolean exec = false;
		
		if(rows > 0)
			exec = true;
		
		return exec;
	}
	
	public int queryRows(String query) {
		ResultSet result = null;
		int rows = 0;
		
		try {
			statement = connection.prepareStatement(query + ";");
			result = statement.executeQuery();
			
			while(result.next()) {
				rows++;
			}
			
			result.close();
			statement.close();
		} catch (SQLException e) {
			rows = 0;
			Log.sqlLn("La consulta: " + query + " tipo \"queryRows\" no se pudo ejecutar");
		}
		
		statement = null;
		
		return rows;
	}
	
	public ResultSet query(String query) {
		ResultSet result = null;
		
		try {
			statement = connection.prepareStatement(query + ";");
			result = statement.executeQuery();
		} catch (SQLException e) {
			Log.sqlLn("La consulta: " + query + " tipo \"query\" no se pudo ejecutar");
		}
		
		statement = null;

		return result;
	}
	
	public HashMap<String, Object> queryMapUnique(String query) {
		ResultSet result = null;
		HashMap<String, Object> results = null;
		
		try {
			statement = connection.prepareStatement(query + ";");
			result = statement.executeQuery();
			
			int colums = result.getMetaData().getColumnCount();
			
			if(result.next()) {
				results = new HashMap<String, Object>(colums);
				
				for(int i = 1; i <= colums; i++) {
					results.put(result.getMetaData().getColumnLabel(i), result.getObject(i));
				}
			}
			
			result.close();
			statement.close();
		} catch (SQLException e) {
			Log.sqlLn("La consulta: " + query + " tipo \"queryMapUnique\" no se pudo ejecutar");
		}
		
		statement = null;
		
		return results;		
	}
	
	public HashMap<Integer, HashMap<String, Object>> queryMap(String query) {
		ResultSet result = null;
		HashMap<Integer, HashMap<String, Object>> results = new HashMap<Integer, HashMap<String, Object>>();
		
		try {
			statement = connection.prepareStatement(query + ";");
			result = statement.executeQuery();
			
			int colums = result.getMetaData().getColumnCount();
			int dataCount = 1;
			
			while(result.next()) {
				HashMap<String, Object> rows = new HashMap<String, Object>(colums);
				
				for(int i = 1; i <= colums; i++) {
					rows.put(result.getMetaData().getColumnLabel(i), result.getObject(i));
				}
				
				results.put(dataCount, rows);
				
				dataCount++;
			}
			
			result.close();
			statement.close();
		} catch (SQLException e) {
			Log.sqlLn("La consulta: " + query + " tipo \"queryMap\" no se pudo ejecutar");
		}
		
		statement = null;
		
		return results;
	}
	
	public List<Object> queryListUnique(String query) {
		ResultSet result = null;
		List<Object> results = null;
		
		try {
			statement = connection.prepareStatement(query + ";");
			result = statement.executeQuery();
			
			int colums = result.getMetaData().getColumnCount();
			
			if(result.next()) {
				results = new ArrayList<Object>(colums);
				
				for(int i = 1; i <= colums; i++) {
					results.add(result.getObject(i));
				}
			}
			
			result.close();
			statement.close();
		} catch (SQLException e) {
			Log.sqlLn("La consulta: " + query + " tipo \"queryListUnique\" no se pudo ejecutar");
		}
		
		statement = null;
		
		return results;
	}
	
	public List<List<Object>> queryList(String query) {
		ResultSet result = null;
		List<List<Object>> results = new ArrayList<List<Object>>();
		
		try {
			statement = connection.prepareStatement(query + ";");
			result = statement.executeQuery();
			
			int colums = result.getMetaData().getColumnCount();
			
			while(result.next()) {
				List<Object> rows = new ArrayList<Object>(colums);
				
				for(int i = 1; i <= colums; i++) {
					rows.add(result.getObject(i));
				}
				
				results.add(rows);
			}
			
			result.close();
			statement.close();
		} catch (SQLException e) {
			Log.sqlLn("La consulta: " + query + " tipo \"queryList\" no se pudo ejecutar");
		}
		
		statement = null;
		
		return results;
	}
	
	public int queryNumRows(String query) {
		ResultSet result = null;
		int numRows = 0;
		
		try {
			statement = connection.prepareStatement(query + ";");
			result = statement.executeQuery();
			
			while(result.next()) {
				numRows++;
			}
			
			result.close();
			statement.close();
		} catch (SQLException e) {
			Log.sqlLn("La consulta: " + query + " tipo \"queryNumRows\" no se pudo ejecutar");
		}
		
		statement = null;
		
		return numRows;
	}
}
