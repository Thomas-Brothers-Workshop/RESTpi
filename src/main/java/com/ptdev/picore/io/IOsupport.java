package com.ptdev.picore.io;

public class IOsupport {
	
	public static int getChipIndex(int pinIndex) {
		return (int) Math.ceil((double)pinIndex/16.0);
	}
}
