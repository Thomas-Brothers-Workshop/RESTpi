package com.ptdev.picore.actions;

import java.util.ArrayList;
import java.util.List;


public class SequenceContext {
	private static SequenceContext instance;
	private List<String> currSequence;
	
	private SequenceContext() {
		currSequence = new ArrayList<String>(); 
	}
	
	public static synchronized SequenceContext getInstance() {
		if (instance == null) {
			System.out.println("Creating new IO context.");
			instance = new SequenceContext();
		}

		return instance;
	}
	
	public void trackSequence(String sequence) {
		if (!isSequenceRunning(sequence)) {
			currSequence.add(sequence);
		}
	}
	
	public void forgetSequence(String sequence) {
		if (isSequenceRunning(sequence)) {
			currSequence.remove(sequence);
		}
	}
	
	public boolean isSequenceRunning(String sequence) {
		return currSequence.contains(sequence);
	}
	
}
