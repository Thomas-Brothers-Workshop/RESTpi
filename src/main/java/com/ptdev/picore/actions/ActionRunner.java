package com.ptdev.picore.actions;

/**
 * This class wraps around actions to run them in different threads
 * @author perry
 *
 */
public class ActionRunner implements Runnable {
	
	private String name;
	private Thread t;
	private final BaseAction action;
	private boolean isEnd;
	private String parentName;
	
	//Construct
	public ActionRunner(String runnerName, BaseAction runAction) {
		this.name = runnerName;
		this.action = runAction;
	}
	
	public ActionRunner setEndOfAction(boolean isEnd) {
		this.isEnd = isEnd;
		return this;
	}
	
	public ActionRunner setParentName(String name) {
		this.parentName = name;
		return this;
	}
	
	@Override
	public void run() {
		//Start action
		this.action.setParentName(parentName).setEndOfAction(isEnd).start();
	}
	
	/**
	 * Threads the action 'start' method
	 */
	public void start ()
	   {
	      if (t == null)
	      {
	         t = new Thread (this, this.name);
	         t.start ();
	         System.out.println(String.format("Started action thread '%s'", this.name));
	      }
	   } 
}
