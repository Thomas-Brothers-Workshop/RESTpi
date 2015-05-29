package com.ptdev.halloween.process;

public abstract class BaseAction {
	//Types
	public enum ActionType {
		IO,
		SOUND,
		DELAY
	}
	
	protected ActionType actionType;
	protected long actionTime;
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
}
