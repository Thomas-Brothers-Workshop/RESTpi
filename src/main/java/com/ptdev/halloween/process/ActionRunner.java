package com.ptdev.halloween.process;

public class ActionRunner implements Runnable {
	
	private String name;
	private Thread t;
	private final BaseAction action;
	
	//Construct
	public ActionRunner(String runnerName, BaseAction runAction) {
		this.name = runnerName;
		this.action = runAction;
	}
	
	@Override
	public void run() {
		//Start action
		this.action.start();
	}
	
	public void start ()
	   {
	      if (t == null)
	      {
	         t = new Thread (this, this.name);
	         t.start ();
	      }
	   } 
}
