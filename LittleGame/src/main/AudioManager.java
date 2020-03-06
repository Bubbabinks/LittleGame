package main;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioManager {
	
	private AudioInputStream backgroundSong;
	private Clip backgroundClip;
	
	public AudioManager() {
		try {
			backgroundSong = AudioSystem.getAudioInputStream(AudioManager.class.getClassLoader().getResource("audio/background.wav"));
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void buttonPressed() {
		try {
			AudioInputStream buttonPressed = AudioSystem.getAudioInputStream(AudioManager.class.getClassLoader().getResource("audio/buttonPress.wav"));
			Clip button = AudioSystem.getClip();
			button.open(buttonPressed);
			button.start();
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	public void blockBreak() {
		try {
			AudioInputStream blockBreakStream = AudioSystem.getAudioInputStream(AudioManager.class.getClassLoader().getResource("audio/blockBreak.wav"));
			Clip blockBreak = AudioSystem.getClip();
			blockBreak.open(blockBreakStream);
			blockBreak.start();
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	public void jump() {
		try {
			AudioInputStream jumpStream = AudioSystem.getAudioInputStream(AudioManager.class.getClassLoader().getResource("audio/jump.wav"));
			Clip jump = AudioSystem.getClip();
			jump.open(jumpStream);
			jump.start();
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	public void startBackgroundMusic() {
		try {
			backgroundClip = AudioSystem.getClip();
			backgroundClip.open(backgroundSong);
			backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
			backgroundClip.start();
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopBackgroundMusic() {
		backgroundClip.stop();
		try {
			backgroundSong.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
