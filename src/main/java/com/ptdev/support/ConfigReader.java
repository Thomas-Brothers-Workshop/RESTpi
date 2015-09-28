package com.ptdev.support;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.ptdev.exceptions.ConfigDirectoryException;
import com.ptdev.exceptions.InvalidConfigSetupException;
import com.ptdev.picore.actions.Sequence;
import com.ptdev.picore.io.Mcp23017;

public class ConfigReader {
	
	private final String configPath;
	private final String name;
	private final Map<Integer, Mcp23017> mcpChip;
	
	public ConfigReader(String name, Map<Integer, Mcp23017> mcpChip) throws ConfigDirectoryException {
		this.name = name;
		this.mcpChip = mcpChip;
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
	
	public Sequence getSequence() throws FileNotFoundException, YamlException, InvalidConfigSetupException {
		YamlReader reader = new YamlReader(new FileReader(String.format("%s/%s.yaml", this.configPath,this.name)));
		SequenceBuilder object = reader.read(SequenceBuilder.class);
		return object.build(mcpChip);
	}
}
