package com.ptdev.halloween.process;

import java.util.HashMap;
import java.util.Map;

public class Sequence {

	private Map<Integer, BaseAction> sequence = new HashMap<Integer, BaseAction>();
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
		sequence.put(index, action);
	}
	
	//Execution
	public void start() {
		//Run in sequence
		for(int a = 0; a < sequence.size(); a++) {
			sequence.get(a).start(); //TODO this should actually call a thread that calls the action
		}
	}
}
