package org.ch.tan.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {
	
	private static Properties props = create();

	private static Properties create() {
		Properties p = new Properties();
		try {
			p = new Properties();
			InputStream  inStream = Props.class.getClassLoader().getResourceAsStream("credentials.properties");
			p.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public Properties get() {
		return props;
	}
	
	public static String getLogin() {
		return props.getProperty("login");
	}
	
	public static String getPassword() {
		return props.getProperty("password");
	}


}
