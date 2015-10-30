package com.ptdev.support;

import com.ptdev.exceptions.InvalidConfigSetupException;
import com.ptdev.picore.actions.Sequence;
import com.ptdev.picore.actions.SequenceContext;

public class SequenceBuilder {
	public String name;
	public ActionBuilder[] actions;
	
	public Sequence build() throws InvalidConfigSetupException {
		Sequence seq = new Sequence(name);
		//If sequence is running then don't re-run (return empty sequence)
		if(SequenceContext.getInstance().isSequenceRunning(name)) {
			System.out.println("Sequence already running: " + name);
			return seq;
		}
		
		System.out.println(String.format("Building sequence '%s' with %d actions", name, actions.length));
		SequenceContext.getInstance().trackSequence(name);
		for(int i = 0; i < actions.length; i++) {
			seq.addAction(actions[i].build());
		}
		return seq;
	}
}
