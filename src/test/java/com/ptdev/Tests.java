package com.ptdev;

import java.io.FileNotFoundException;

import org.junit.Test;

import com.esotericsoftware.yamlbeans.YamlException;
import com.ptdev.support.ConfigReader;

import exceptions.ConfigDirectoryException;

public class Tests {
	
	@Test
	public void configTest() {
		System.setProperty("config_dir", "/Users/admin/git/ForFun/src/main/resources/com/ptdev/");
		try {
			new ConfigReader("exampleSequence").readConfig();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (YamlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigDirectoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
