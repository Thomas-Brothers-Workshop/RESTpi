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
