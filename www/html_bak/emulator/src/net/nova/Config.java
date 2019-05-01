package net.nova;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private static Properties config = null;
	
	public static void init() throws FileNotFoundException, IOException {
		FileInputStream fileReader = new FileInputStream(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "config.properties"));
		config = new Properties();
		config.load(fileReader);
	}
	
	public static Object getProperty(String key) {
		if(config == null)
			return null;
		
		return config.getProperty(key);
	}
	
	public static Object getProperty(String key, Object def) {
		if(config == null)
			return def;
		
		return config.getProperty(key, (String) def);
	}
}
