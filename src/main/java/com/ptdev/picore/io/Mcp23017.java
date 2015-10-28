package com.ptdev.picore.io;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.i2c.I2CBus;

public class Mcp23017 {
	
	//Address options
	public enum ByteAddress {
		ONE(0x20,0),
		TWO(0x21,1),
		THREE(0x20,2),
		FOUR(0x20,3),
		FIVE(0x20,4),
		SIX(0x20,5),
		SEVEN(0x20,6);
		
		private int byteAddress;
		private int multi;
		
		ByteAddress(int address, int multiplier) {
			this.byteAddress = address;
			this.multi = multiplier;
		}
		public int getAddress() {
			return this.byteAddress;
		}
		public int getMutiplier() {
			return this.multi;
		}
	}

	//Properties
	private int bus;
	private ByteAddress address;
	private String key;
	public Map<Integer, McpOutputPin> outputPins;
	
	//Provider
	private GpioController gpio;
	private MCP23017GpioProvider provider;
	
	public Mcp23017(GpioController gpioControl, ByteAddress chipAdress, String uniqueKey) {
		this.gpio = gpioControl;
		this.bus = I2CBus.BUS_1;
		this.address = chipAdress;
		this.key = uniqueKey;
		
		//Setup provider
		try {
			provider = new MCP23017GpioProvider(this.bus, address.byteAddress);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Properties
	public ByteAddress getAddress() {
		return address;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}	
	
	//Functionality
	public Mcp23017 setAllPinsOutput() {
		int[] allPins = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		this.outputPins = getOutputPins(allPins, true);
		return this;
	}
	
	private McpOutputPin setOutputPin(int pinIndex, boolean startState) {
		return new McpOutputPin(gpio, provider, pinIndex, "MCP-Out-" + pinIndex, startState);
	}
	
	private Map<Integer, McpOutputPin> getOutputPins(int[] pinIndexes, boolean startState) {
		Map<Integer, McpOutputPin> pins = new HashMap<Integer, McpOutputPin>();
		for (int pin : pinIndexes) {
			
			pins.put((pin + 1)+(address.multi*16), setOutputPin(pin, startState));
		}
		return pins;
	}
}
