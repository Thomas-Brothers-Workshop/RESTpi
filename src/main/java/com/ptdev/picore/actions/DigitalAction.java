package com.ptdev.picore.actions;

import com.ptdev.picore.io.McpOutputPin;

/**
 * This class handles actions to turn pins on or off with parameters
 * @author perry
 *
 */
public class DigitalAction extends BaseAction {
	
	public enum IoState {
		ON,
		OFF,
		TOGGLE;
		
		public static IoState getStateByString(String state) {
			switch (state.toLowerCase()) {
			case "on":
				return ON;
			case "off":
				return OFF;
			case "toggle":
				return TOGGLE;
			default:
				return OFF;
			}
		}
	}
	
	private McpOutputPin pin;
	private IoState state;
	
	//Construction
	public DigitalAction(McpOutputPin pin, IoState setState, long timeMill, long delayMill) {
		this.construct(pin, setState, timeMill, delayMill);
	}
	
	public DigitalAction(McpOutputPin pin, IoState setState, long timeMill) {
		this.construct(pin, setState, timeMill, 0);
	}
	
	public DigitalAction(McpOutputPin pin, IoState setState) {
		this.construct(pin, setState, -1, 0);
	}
	
	public DigitalAction(McpOutputPin pin) {
		this.construct(pin, IoState.TOGGLE, -1, 0);
	}
	
	private void construct(McpOutputPin pin, IoState setState,long time, long delay) {
		this.actionType = ActionType.IO;
		this.pin = pin;
		this.state = setState;
		this.actionTime = time;
		this.actionDelay = delay;
		this.actionKey = "IO-" + pin.getPinName();
	}

	@Override
	public void start() {
		
		//Report
		System.out.println(String.format("Action | PIN: %d | STATE: %s | TIME: %d | DELAY: %d", 
				pin.getPinIndex(), 
				state.toString(),
				actionTime, 
				actionDelay));
		
		//Delay
		try {
			this.delay(actionDelay);
		} catch(Exception e) {
			System.out.println("Thread error. Aborting action");
			return;
		}
		
		//Switch area
		switch (this.state) {
			case ON:
				pin.turnOn();
				break;
			case OFF:
				pin.turnOff();
				break;
			case TOGGLE:
				pin.toggle();
				break;
			default:
				System.out.println("Invalid pin state: " + this.state.toString());
				break;
		}
		
		//If a time was set, wait that amount of time then toggle
		if (this.actionTime > 0) {
			try {
				System.out.println("Sleeping for '"+ actionTime +"' milliseconds");
				Thread.sleep(this.actionTime);
				pin.toggle();
			} catch (Exception e) {
				System.out.println("Thread error. Turning pin off");
				pin.turnOff();
			} 
		}
		checkForEnd();
	}

	//Properties
	public McpOutputPin getPin() {
		return pin;
	}

	public IoState getState() {
		return state;
	}
}
