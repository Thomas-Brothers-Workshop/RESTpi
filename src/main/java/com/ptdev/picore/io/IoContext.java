package com.ptdev.picore.io;

import java.util.HashMap;
import java.util.Map;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.ptdev.picore.io.Mcp23017.ByteAddress;

public class IoContext {
	private static IoContext instance;
	private GpioController gpio;
	private Mcp23017 chipOne;
	private Mcp23017 chipTwo; 
	private Map<Integer, Mcp23017> mcpMap = new HashMap<Integer, Mcp23017>();;
	private GpioPinDigitalOutput testPin;
	
	private IoContext() {
		try {
			//Chips
			gpio = GpioFactory.getInstance();
			System.out.println("GPIO Info: " + gpio.toString());
			
			
			// set shutdown state for this pin
			testPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
			testPin.setShutdownOptions(true, PinState.LOW);
			
			chipOne = new Mcp23017(gpio, ByteAddress.ONE, "one").setAllPinsOutput();
			chipTwo = new Mcp23017(GpioFactory.getInstance(), ByteAddress.TWO, "one").setAllPinsOutput();
			
			//Map
			mcpMap.put(1, chipOne);
			mcpMap.put(2, chipTwo);
		} catch (NullPointerException e) {
			System.err.println("Looks like I couldn't find the IO expanders.");
			e.printStackTrace();
		}
	}
	
	public static synchronized IoContext getInstance() {
		if (instance == null) {
			System.out.println("Creating new IO context.");
			instance = new IoContext();
		}

		return instance;
	}
	
	public Map<Integer, Mcp23017> getMcpMap() {
		return mcpMap;
	}
	
	public GpioPinDigitalOutput getTestPin() {
		return testPin;
	}
	
	public GpioController getGPIO() {
		return gpio;
	}
}
