package net.nova.sqlstorage;

public interface SqlDataObject {
	public String get(String field);
	
	public void set(String field, String value);
	
	public void refresh();
	
	public void clean();
}
