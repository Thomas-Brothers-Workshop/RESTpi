package com.ptdev.support;

import com.ptdev.exceptions.InvalidConfigSetupException;
import com.ptdev.picore.actions.Sequence;

public class SequenceBuilder {
	public String name;
	public ActionBuilder[] actions;
	
	public Sequence build() throws InvalidConfigSetupException {
		Sequence seq = new Sequence(name);
		System.out.println(String.format("Building sequence '%s' with %d actions", name, actions.length));
		for(int i = 0; i < actions.length; i++) {
			seq.addAction(actions[i].build());
		}
		return seq;
	}
}
