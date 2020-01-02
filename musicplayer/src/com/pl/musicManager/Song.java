package com.pl.musicManager;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Song {
	
	private Long currentFrame;
	private Clip clip;
	private Details songDetails;
	private AudioInputStream audioInputStream;
	private String status;
	
	public Song(File file, AudioInputStream audioInputStream) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.audioInputStream = AudioSystem.getAudioInputStream(file);
		this.clip = AudioSystem.getClip();
	}
	
	public void play() throws LineUnavailableException, IOException {
		clip.loop(0);
		clip.start();
		status = "play";
	}


}
