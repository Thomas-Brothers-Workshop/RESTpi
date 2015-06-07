package com.ptdev.picore.actions;

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
	
	public abstract void start();
	
	public void delay(long milliseconds) throws InterruptedException {
		if(milliseconds > 0) {
			Thread.sleep(milliseconds);
		}
	}
}
