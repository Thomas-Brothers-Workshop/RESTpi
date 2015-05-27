package com.ptdev.halloween.io;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.i2c.I2CBus;

public class Mcp23017 {

	private I2CBus bus;
	private int address;
	private String key;
	
	//Provider
	private final GpioController gpio;
	private final MCP23017GpioProvider provider;
	
	public Mcp23017(GpioController gpioControl, I2CBus bus, int byteAddress, String uniqueKey) throws IOException {
		this.gpio = gpioControl;
		this.bus = bus;
		this.address = byteAddress;
		this.key = uniqueKey;
		
		//Setup provider
		provider = new MCP23017GpioProvider(this.bus, address);
	}
	//Properties
	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}	
	
	//Functionality
	public McpOutputPin getOutputPin(int pinIndex, boolean startState) {
		return new McpOutputPin(gpio, provider, pinIndex, "MCP-Out-" + pinIndex, startState);
	}
	
	public Map<Integer, McpOutputPin> getOutputPins(int[] pinIndexes, boolean startState) {
		Map<Integer, McpOutputPin> pins = new HashMap<Integer, McpOutputPin>();
		for (int pin : pinIndexes) {
			pins.put(pin, getOutputPin(pin, startState));
		}
		return pins;
	}
}
