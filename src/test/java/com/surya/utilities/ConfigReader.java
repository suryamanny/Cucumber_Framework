package com.surya.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties prop;
	
	public static void loadConfig() {
		try {
		prop = new Properties();
		FileInputStream file = new FileInputStream("src/test/resources/config/config.properties");
		  prop.load(file);
	    }
		catch(IOException e) {
			 e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		if(prop==null) {
			loadConfig();
		}
		return prop.getProperty(key);	
	}
	

}
