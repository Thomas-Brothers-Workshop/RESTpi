package com.ptdev.picore.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;

import com.ptdev.picore.io.McpOutputPin;
import com.ptdev.support.SoundPlayer;

public class SoundAction extends BaseAction {
	
	private final McpOutputPin pin;
	private final SoundType soundType;
	private String rootDirectory = "/home/pi/HalloweenPi/WebIOpi/Sound";
	
	public enum SoundType {
		ONE("Type1"),
		TWO("Type2"),
		THREE("Type3"),
		FOUR("Type4");
		
		private String directory;
		
		private SoundType(String directory) {
			this.directory = directory;
		}
		
		public static SoundType getTypeByString(String type) {
			switch (type.toLowerCase()) {
			case "one":
				return ONE;
			case "two":
				return TWO;
			case "three":
				return THREE;
			case "four":
				return FOUR;
			default:
				throw new IllegalArgumentException("Sound type is not supported: " + type);
			}
		}
	}
	
	public SoundAction(McpOutputPin pin, SoundType type) {
		this.actionType = ActionType.SOUND;
		this.pin = pin;
		this.soundType = type;
		this.actionKey = "SOUND-" + this.pin.getPinName();
	}
	
	public String getRandomSound() throws FileNotFoundException {
		String dirPath = String.format("%s/%s", rootDirectory, this.soundType.directory);
		File dir = new File(dirPath);
		String[] children = dir.list();
		//Check for files
		if (children.length < 1) {
			throw new FileNotFoundException("No sound files found at: " + dirPath);
		}
		
		Collections.shuffle(Arrays.asList(children));
		return dirPath + "/" + children[0];
	}

	@Override
	public void start() {
		//Check if action is active
		if(SequenceContext.getInstance().isActionRunning(actionKey)) {
			return;
		}
		
		//Report
		System.out.println("Sound type: " + soundType.toString() + " on pin " + pin.getPinIndex());
		SequenceContext.getInstance().trackAction(actionKey);
		
		//Play music, turn on pin, turn off after music
		SoundPlayer player = new SoundPlayer();
		try {
			player.play(getRandomSound());
		} catch (FileNotFoundException e) {
			//End action if issue occurs
			return;
		}
		
		//Turn pin on and wait if sound was successful
		if (!player.isSoundDone()) {
			pin.turnOn();
			
			//Wait for sound to finish
			long timeout = System.currentTimeMillis() + 60 * 1000;
			while (!player.isSoundDone() && System.currentTimeMillis() < timeout) {
				//Do nothing here
			}
			
			//Turn off pin
			pin.turnOff();
		}
		
		//Forget action
		SequenceContext.getInstance().forgetAction(actionKey);
	}
}
