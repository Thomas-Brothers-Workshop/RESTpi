package com.ptdev.support;

import java.util.List;

import com.ptdev.exceptions.InvalidConfigSetupException;
import com.ptdev.picore.actions.Sequence;
import com.ptdev.picore.io.IOsupport;
import com.ptdev.picore.io.IoContext;
import com.ptdev.picore.io.Mcp23017;

public class SequenceBuilder {
	public String name;
	public List<ActionBuilder> actions;
	
	public Sequence build() throws InvalidConfigSetupException {
		Sequence seq = new Sequence(name);
		System.out.println(String.format("Building sequence '%s'", name));
		System.out.println("Found " + actions.size() + " actions in sequence.");
		for(int i = 0; i < actions.size(); i++) {
			seq.addAction(actions.get(i).build());
		}
		return seq;
	}
}
