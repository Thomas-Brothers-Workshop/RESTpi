package com.ptdev.support;

import java.util.List;

import com.ptdev.exceptions.InvalidConfigSetupException;
import com.ptdev.picore.actions.Sequence;
import com.ptdev.picore.io.IOsupport;
import com.ptdev.picore.io.IoContext;

public class SequenceBuilder {
	public String name;
	public List<ActionBuilder> actions;
	
	public Sequence build() throws InvalidConfigSetupException {
		Sequence seq = new Sequence(name);
		System.out.println(String.format("Building sequence '%s'", name));
		for(ActionBuilder action : actions) {
			seq.addAction(action.build(IoContext.getInstance().getMcpMap().get(IOsupport.getChipIndex(action.pin))));
		}
		return seq;
	}
}
