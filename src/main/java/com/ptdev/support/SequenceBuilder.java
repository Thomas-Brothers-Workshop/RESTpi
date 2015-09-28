package com.ptdev.support;

import java.util.List;
import java.util.Map;

import com.ptdev.exceptions.InvalidConfigSetupException;
import com.ptdev.picore.actions.Sequence;
import com.ptdev.picore.io.Mcp23017;

public class SequenceBuilder {
	public String name;
	public List<ActionBuilder> actions;
	
	public Sequence build(Map<Integer, Mcp23017> mcpChip) throws InvalidConfigSetupException {
		Sequence seq = new Sequence(name);
		System.out.println(String.format("Building sequence '%s'", name));
		for(ActionBuilder action : actions) {
			seq.addAction(action.build(mcpChip));
		}
		return seq;
	}
}
