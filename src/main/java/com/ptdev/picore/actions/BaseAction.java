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
	protected boolean isEnd;
	protected String parentSequenceName;
	
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
	
	public BaseAction setEndOfAction(boolean isEnd) {
		this.isEnd = isEnd;
		return this;
	}
	
	public BaseAction setParentName(String name) {
		this.parentSequenceName = name;
		return this;
	}
	
	public void checkForEnd() {
		if(this.isEnd) {
			SequenceContext.getInstance().forgetSequence(parentSequenceName);
		}
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
