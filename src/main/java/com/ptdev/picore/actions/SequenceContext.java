package com.ptdev.picore.actions;

import java.util.ArrayList;
import java.util.List;


public class SequenceContext {
	private static SequenceContext instance;
	private List<String> currAction;
	
	private SequenceContext() {
		currAction = new ArrayList<String>(); 
	}
	
	public static synchronized SequenceContext getInstance() {
		if (instance == null) {
			System.out.println("Creating new IO context.");
			instance = new SequenceContext();
		}

		return instance;
	}
	
	public void trackAction(String action) {
		if (!isActionRunning(action)) {
			currAction.add(action);
		}
	}
	
	public void forgetAction(String action) {
		if (isActionRunning(action)) {
			currAction.remove(action);
		}
	}
	
	public boolean isActionRunning(String action) {
		return currAction.contains(action);
	}
	
}
