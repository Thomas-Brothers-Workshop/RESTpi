package com.ptdev.support;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.ptdev.exceptions.ConfigDirectoryException;

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
	
	//TODO still need to produce a sequence
	public void readConfig() throws FileNotFoundException, YamlException {
		YamlReader reader = new YamlReader(new FileReader(String.format("%s/%s.yaml", this.configPath,this.name)));
		SequenceBuilder object = reader.read(SequenceBuilder.class);
		System.out.println("test");
	}
	
//	public Sequence getSequence() {
//		
//	}
}
