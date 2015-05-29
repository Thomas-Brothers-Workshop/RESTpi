package com.ptdev.picore.actions;

import com.ptdev.picore.io.McpOutputPin;

public class DigitalAction extends BaseAction {
	
	public enum IoState {
		ON,
		OFF,
		TOGGLE
	}
	
	private McpOutputPin pin;
	private IoState state;
	
	//Construction
	public DigitalAction(McpOutputPin pin, IoState setState, long timeMill) {
		this.construct(pin, setState, timeMill);
	}
	
	public DigitalAction(McpOutputPin pin, IoState setState) {
		this.construct(pin, setState, -1);
	}
	
	public DigitalAction(McpOutputPin pin) {
		this.construct(pin, IoState.TOGGLE, -1);
	}
	
	private void construct(McpOutputPin pin, IoState setState,long time) {
		this.actionType = ActionType.IO;
		this.pin = pin;
		this.state = setState;
		this.actionTime = time;
		this.actionKey = "IO-" + time;
	}

	@Override
	public void start() {
		switch (this.state) {
			case ON:
				pin.turnOn();
			case OFF:
				pin.turnOff();
			case TOGGLE:
				pin.toggle();
			default:
				System.out.println("Invalid pin state: " + this.state.toString());
		}
		
		//If a time was set, wait that amount of time then toggle
		if (this.actionTime > 0) {
			try {
				Thread.sleep(this.actionTime);
				pin.toggle();
			} catch (Exception e) {
				System.out.println("Thread error. Turning pin off");
				pin.turnOff();
			} 
		}
	}

	//Properties
	public McpOutputPin getPin() {
		return pin;
	}

	public IoState getState() {
		return state;
	}
}
