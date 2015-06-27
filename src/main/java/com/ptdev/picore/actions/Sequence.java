package com.ptdev.picore.actions;

import java.util.HashMap;
import java.util.Map;

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
	public void addAction(ActionRunner action) {
		//Put action next in line
		int index = (sequence.isEmpty()) ? 0 : sequence.size();
		
		//Add action
		sequence.put(index, action);
	}
	
	//Execution
	public void start() {
		//Run in sequence
		System.out.println(String.format("Starting '%s' sequence", this.sequenceName));
		for(int a = 0; a < sequence.size(); a++) {
			sequence.get(a).start();
		}
	}
}
