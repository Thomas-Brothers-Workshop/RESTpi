package com.ptdev.halloween.io;

import com.pi4j.gpio.extension.mcp.MCP23017GpioProvider;
import com.pi4j.gpio.extension.mcp.MCP23017Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

public class McpOutputPin {
	//Pin properties
	private int pinIndex;
	private String pinName;
	
	//GPIO Handlers
	private final GpioController gpio;
	private final MCP23017GpioProvider mcpProvider;
	private final GpioPinDigitalOutput pin;
	
	//Construction
	public McpOutputPin(GpioController gpioControl, MCP23017GpioProvider mcpProvider, int index, String pinName, boolean startState) {
		this.gpio = gpioControl;
		this.mcpProvider = mcpProvider;
		this.pinIndex = index;
		this.pinName = pinName;
		this.pin = gpio.provisionDigitalOutputPin(this.mcpProvider, getMcpPinByIndex(index), pinName, getPinStateByBool(startState));
	}
	
	//Properties
	public int getPinIndex() {
		return pinIndex;
	}

	public void setPinIndex(int pinIndex) {
		this.pinIndex = pinIndex;
	}

	public String getPinName() {
		return pinName;
	}

	public void setPinName(String pinName) {
		this.pinName = pinName;
	}
	
	//Actions
	public void turnOn() {
		pin.setState(getPinStateByBool(true));
	}
	
	public void turnOff() {
		pin.setState(getPinStateByBool(false));
	}
	
	public void toggle() {
		pin.toggle();
	}
	
	public boolean getState() {
		return getBoolByPinState(pin.getState());
	}
	
	//Private helpers
	private Pin getMcpPinByIndex(int index) {
		return MCP23017Pin.ALL[index];
	}
	
	private PinState getPinStateByBool(boolean pinState) {
		return (pinState) ? PinState.HIGH : PinState.LOW;
	}
	
	private boolean getBoolByPinState(PinState state) {
		return (state.isHigh()) ? true : false;
	}
}
