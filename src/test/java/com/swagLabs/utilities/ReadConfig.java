package com.swagLabs.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties properties;
	FileInputStream file;

	String path = System.getProperty("user.dir") + "\\Configuration\\config.properties";

	public ReadConfig() {

		try {
			properties = new Properties();
			file = new FileInputStream(path);
			properties.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getAppURL() {
		String browserURL = properties.getProperty("URL");
		if (browserURL != null) {
			return browserURL;
		} else {

			throw new RuntimeException("URL not specified in config.properties file");
		}
	}

	public String getBrowser() {
		String browserName = properties.getProperty("browser");
		if (browserName != null) {
			return browserName;
		} else {

			throw new RuntimeException("browser not specified in config.properties file");
		}
	}
	public String getCartProduct1() {
		String itemName = properties.getProperty("aadCartItem1");
		if (itemName != null) {
			return itemName;
		} else {

			throw new RuntimeException("browser not specified in config.properties file");
		}
	}
	public String getCartProduct2() {
		String itemName = properties.getProperty("aadCartItem2");
		if (itemName != null) {
			return itemName;
		} else {

			throw new RuntimeException("browser not specified in config.properties file");
		}
	}
	public String getCartProduct3() {
		String itemName = properties.getProperty("aadCartItem3");
		if (itemName != null) {
			return itemName;
		} else {

			throw new RuntimeException("browser not specified in config.properties file");
		}
	}

}
