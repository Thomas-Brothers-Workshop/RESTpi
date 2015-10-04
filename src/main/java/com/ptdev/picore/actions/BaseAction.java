package com.ptdev.picore.actions;

/**
 * This is the super class for all actions being created
 * @author perry
 *
 */
public abstract class BaseAction {
	//Types
	public enum ActionType {
		IO,
		SOUND
	}
	
	protected ActionType actionType;
	protected long actionTime;
	protected long actionDelay;
	protected String actionKey;
	
	//Get Info
	public ActionType getActionType() {
		return actionType;
	}
	public long getActionTime() {
		return actionTime;
	}
	public String getActionKey() {
		return actionKey;
	}
	
	/**
	 * All actions must have a start method that contains their base process
	 */
	public abstract void start();
	
	public void delay(long milliseconds) throws InterruptedException {
		if(milliseconds > 0) {
			Thread.sleep(milliseconds);
		}
	}
}
