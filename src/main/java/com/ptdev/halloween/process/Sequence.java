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
		//TODO need to figure out how we are going to multi-thread this
	}
}
