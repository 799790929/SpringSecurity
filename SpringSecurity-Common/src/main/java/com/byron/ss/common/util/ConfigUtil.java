package com.byron.ss.common.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

	private static Properties props = new Properties();
	static {
		InputStream ips = 
				ConfigUtil.class.getClassLoader()
				.getResourceAsStream("config/ezSales.properties");
		try {
			props.load(ips);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
}
