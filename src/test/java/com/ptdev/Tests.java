package com.ptdev;

import org.junit.Test;

import com.ptdev.picore.io.IOsupport;

public class Tests {
	
//	@Test
//	public void configTest() {
//		System.setProperty("config_dir", "/Users/admin/git/ForFun/src/main/resources/com/ptdev/");
////		try {
////			//new ConfigReader("exampleSequence").getSequence();
////		} catch (FileNotFoundException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (YamlException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (ConfigDirectoryException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	}
	
	@Test
	public void chipIndexTest() {
		assert IOsupport.getChipIndex(16) == 1;
		assert IOsupport.getChipIndex(5) == 1;
		assert IOsupport.getChipIndex(17) == 2;
		assert IOsupport.getChipIndex(33) == 3;
	}
}
