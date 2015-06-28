package com.ptdev.support;

import com.ptdev.exceptions.InvalidConfigSetupException;
import com.ptdev.picore.actions.BaseAction;
import com.ptdev.picore.actions.DigitalAction;
import com.ptdev.picore.actions.DigitalAction.IoState;
import com.ptdev.picore.io.Mcp23017;

public class ActionBuilder {
	public String type;
	public int pin;
	public String set;
	public long delay;
	public long time;
	
	public BaseAction build(Mcp23017 mcpChip) throws InvalidConfigSetupException {
		switch (this.type.toLowerCase()) {
		case "io":
			return new DigitalAction(mcpChip.outputPins.get(pin), IoState.getStateByString(set), time, delay);
		case "sound":
			//TODO implement this
			return null;
		default:
			throw new InvalidConfigSetupException(String.format("'%s' is an invalid type of action", this.type));
		}
	}
}
