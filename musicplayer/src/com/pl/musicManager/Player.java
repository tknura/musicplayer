package com.pl.musicManager;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public class Player {
	
	private MediaPlayer mediaPlayer;
	private Queue songsQueue;
	private Song currentPlayingSong;
	
	public Queue getSongsQueue() {
		return songsQueue;
	}

	public void addToQueue(Song song) {
		songsQueue.add(song);
	}
	
	public void Play(Song song) {
		Media currentSongMedia = new Media(new File(song.getDirectory()).toURI().toString());
		currentPlayingSong = song;
		currentPlayingSong.setPlaying(false);
		mediaPlayer = new MediaPlayer(currentSongMedia);
		song.setPlaying(true);
		mediaPlayer.play();
	}
	
	public void Pause() {
		mediaPlayer.pause();
	}
}
