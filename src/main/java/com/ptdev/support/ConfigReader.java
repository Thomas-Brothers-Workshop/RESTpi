package com.ptdev.support;


import java.io.File;

import exceptions.ConfigDirectoryException;

public class ConfigReader {
	
	private final String configPath;
	
	public ConfigReader() throws ConfigDirectoryException {
		this.configPath = System.getProperty("config_dir");
		
		//Check for property
		if(null == this.configPath) {
			throw new ConfigDirectoryException("No config_dir");
		}
		
		//Check exists
		File f = new File(this.configPath);
		if(!f.exists() && !f.isDirectory()) {
			throw new ConfigDirectoryException("");
		}
		
	}
	
}
