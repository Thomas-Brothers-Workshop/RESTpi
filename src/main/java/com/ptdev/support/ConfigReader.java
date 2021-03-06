package com.ptdev.support;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.ptdev.exceptions.ConfigDirectoryException;
import com.ptdev.exceptions.InvalidConfigSetupException;
import com.ptdev.picore.actions.Sequence;

public class ConfigReader {
	
	private final String configPath;
	private final String name;
	
	public ConfigReader(String name) throws ConfigDirectoryException {
		this.name = name;
		this.configPath = "/home/pi/PropSequences/";
		System.out.println(String.format("Looking for configuration in '%s'", configPath));
		
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
	
	public Sequence getSequence() throws FileNotFoundException, YamlException, InvalidConfigSetupException {
		System.out.println(String.format("Looking for configuration file '%s'", this.name));
		YamlReader reader = new YamlReader(new FileReader(String.format("%s/%s.yaml", this.configPath, this.name)));
		SequenceBuilder object = reader.read(SequenceBuilder.class);
		return object.build();
	}
}
