package com.ptdev.picore.actions;

import java.util.HashMap;
import java.util.Map;
/**
 * Sequences wrap actions together and run in line
 * @author perry
 *
 */
public class Sequence {

	private Map<Integer, ActionRunner> sequence = new HashMap<Integer, ActionRunner>();
	private final String sequenceName;
	
	//Construct
	public Sequence(String name) {
		this.sequenceName = name;
	}
	public Sequence() {
		this.sequenceName = "blank-sequence";
	}
	
	//Action set
	public void addAction(BaseAction action) {
		//Put action next in line
		int index = (sequence.isEmpty()) ? 0 : sequence.size();
		
		//Add action
		System.out.println(String.format("Adding action, index: %n sequence: %s", index, sequenceName));
		sequence.put(index, new ActionRunner(String.format("Action index: %n Sequence: %s", index, sequenceName), action));
	}
	
	//Execution
	public void start() {
		//Ignore empty sequences
		if(sequence.isEmpty()) {
			return;
		}
		
		//Run in sequence
		System.out.println(String.format("Starting '%s' sequence", this.sequenceName));
		for(int a = 0; a < sequence.size(); a++) {
			//Special case for last thread
			boolean isEnd = false;
			if(a == sequence.size() - 1) {
				isEnd = true;
			}
			sequence.get(a).setParentName(sequenceName).setEndOfAction(isEnd).start();
		}
	}
}
