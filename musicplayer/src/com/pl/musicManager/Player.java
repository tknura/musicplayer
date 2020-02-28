package com.pl.musicManager;

import java.io.File;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Player {
	
	private static MediaPlayer mediaPlayer;
	private  static Queue songsQueue;
	private static Song currentPlayingSong;
	
	public static MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public static void setMediaPlayer(MediaPlayer mediaPlayer) {
		Player.mediaPlayer = mediaPlayer;
	}

	public static Queue getSongsQueue() {
		return songsQueue;
	}

	public static void addToQueue(Song song) {
		songsQueue.add(song);
	}
	
	public static Song getCurrentPlayingSong() {
		return currentPlayingSong;
	}

	public static void setCurrentPlayingSong(Song currentPlayingSong) {
		Player.currentPlayingSong = currentPlayingSong;
	}

	public static void load(Song song) {
		Media currentSongMedia = new Media(new File(song.getDirectory()).toURI().toString());
		currentPlayingSong = song;
		mediaPlayer = new MediaPlayer(currentSongMedia);
	}
	
	public static void load() {
		load(currentPlayingSong);
	}
	
	public static void resume() {
		mediaPlayer.play();	
		currentPlayingSong.setPlaying(true);
	}
	
	public static void pause() {
		mediaPlayer.pause();
		currentPlayingSong.setPlaying(false);
	}
	
	public static ReadOnlyObjectProperty<Duration> currentTimeProperty(){
		return mediaPlayer.currentTimeProperty();
	}
	
	public static void seek(Duration duration) {
		mediaPlayer.seek(duration);
	}
}
