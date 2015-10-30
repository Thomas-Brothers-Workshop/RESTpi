package com.ptdev.support;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This code stolen from web-site
 * @author http://www.codejava.net/coding/how-to-play-back-audio-in-java-with-examples
 *
 */
public class SoundPlayer implements LineListener {
	/**
     * this flag indicates whether the play-back completes or not.
     */
    boolean playCompleted = false;
    private Clip currClip;
     
    /**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     */
    public void play(String audioFilePath) {
    	
        File audioFile = new File(audioFilePath);
        
        //If file doesn't exist, exit player and tell action play is done
        if (!audioFile.exists()) {
        	System.out.println("File did not exist: " + audioFilePath);
        	this.playCompleted = true;
        	return;
        }
        
        System.out.println("Starting sound file: " + audioFilePath);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);     
            audioClip.start();
            this.currClip = audioClip;
             
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            this.playCompleted = true;
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            this.playCompleted = true;
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            this.playCompleted = true;
        }      
    }
    
    public boolean isSoundDone() {
    	return playCompleted;
    }
     
    /**
     * Listens to the START and STOP events of the audio line.
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");    
        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            currClip.stop();
            System.out.println("Playback completed.");
        }
    }
}
