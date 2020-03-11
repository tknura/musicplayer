package com.pl.musicManager;

import java.io.File;
import java.util.LinkedList;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Player{
	
	private static MediaPlayer mediaPlayer;
	private static Queue songsQueue;
	private static Queue userQueue;
	private static Queue lastlyPlayedSongs;
	private static Song currentPlayingSong;
	
	static {
		songsQueue = new Queue();
		userQueue = new Queue();
		lastlyPlayedSongs = new Queue();
	}
	
	public static MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public static void setMediaPlayer(MediaPlayer mediaPlayer) {
		Player.mediaPlayer = mediaPlayer;
	}
	
	public static void setSongsQueue(Queue songsQueue) {
		Player.songsQueue = songsQueue;
	}
	
	public static Queue getSongsQueue() {
		return songsQueue;
	}

	public static void addToQueue(Song song) {
		songsQueue.add(song);
	}
	
	public static Queue getUserQueue() {
		return userQueue;
	}

	public static void setUserQueue(Queue userQueue) {
		Player.userQueue = userQueue;
	}

	public static Queue getLastlyPlayedSongs() {
		return lastlyPlayedSongs;
	}

	public static void setLastlyPlayedSongs(Queue lastlyPlayedSongs) {
		Player.lastlyPlayedSongs = lastlyPlayedSongs;
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
		lastlyPlayedSongs.add(song);
		mediaPlayer = new MediaPlayer(currentSongMedia);
	}
	
	public static void shuffle(boolean state) {
		Queue tmp = songsQueue;
		if(state) {
			tmp = Player.getSongsQueue();
			Player.getSongsQueue().shuffle();
		}
		else {
			if(tmp != null) {
				Player.setSongsQueue(tmp);
			}
		}
	}
	
	public static void repeat(boolean state) {
		if(state) {
			mediaPlayer.setCycleCount(Integer.MAX_VALUE);
		}
		else {
			mediaPlayer.setCycleCount(1);
		}
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
	
	public static void mute() {
		mediaPlayer.setMute(true);
	}
	
	public static void unMute() {
		mediaPlayer.setMute(false);
	}
	
	public static ReadOnlyObjectProperty<Duration> currentTimeProperty(){
		return mediaPlayer.currentTimeProperty();
	}
	
	public static void seek(Duration duration) {
		mediaPlayer.seek(duration);
	}
}
