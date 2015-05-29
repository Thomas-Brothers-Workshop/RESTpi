package com.ptdev.halloween.process;

import com.ptdev.halloween.io.McpOutputPin;

public class DigitalAction extends BaseAction {
	
	public final McpOutputPin pin;
	
	//Construction
	public DigitalAction(McpOutputPin pin, long timeMill) {
		this.pin = pin;
		this.actionType = ActionType.IO;
		this.construct(timeMill);
	}
	
	public DigitalAction(McpOutputPin pin) {
		this.pin = pin;
		this.construct(-1);
	}
	
	private void construct(long time) {
		this.actionTime = time;
		this.actionKey = "IO-" + time;
	}

	@Override
	public void start() {
		//TODO need to figure out how we are going to multi-thread this
	}
}
