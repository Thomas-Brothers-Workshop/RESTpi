package com.ptdev.support;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import exceptions.ConfigDirectoryException;

public class ConfigReader {
	
	private final String configPath;
	private final String name;
	
	public ConfigReader(String name) throws ConfigDirectoryException {
		this.name = name;
		this.configPath = System.getProperty("config_dir");
		
		//Check for property
		if(null == this.configPath) {
			throw new ConfigDirectoryException("No config_dir property set. Run server with -Dconfig_dir arguement");
		}
		
		//Check exists
		File f = new File(this.configPath);
		if(!f.exists() && !f.isDirectory()) {
			throw new ConfigDirectoryException(String.format("'%s' is not a valid path on the server", this.configPath));
		}
	}
	
	public void readConfig() throws FileNotFoundException, YamlException {
		YamlReader reader = new YamlReader(new FileReader(String.format("%s/%s.yaml", this.configPath,this.name)));
		Object object = reader.read();
		System.out.println(object);
		Map map = (Map)object;
		System.out.println(map.get("address"));
	}
	
//	public Sequence getSequence() {
//		
//	}
}
