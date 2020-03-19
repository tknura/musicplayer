package com.pl.musicManager;

import java.io.File;
import java.util.LinkedList;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class Player{
	
	private static MediaPlayer mediaPlayer;
	private static Queue currentPlayingSongList;
	private static Queue userQueue;
	private static Queue lastlyPlayedSongs;
	private static Song currentPlayingSong;
	
	static {
		currentPlayingSongList = new Queue();
		userQueue = new Queue();
		lastlyPlayedSongs = new Queue();
	}
	
	public static MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
	
	public static void setCurrentPlayingSongList(MusicStructure struct) {
		Player.currentPlayingSongList = (Queue)struct;
	}
	
	public static Queue getCurrentPlayingSongList() {
		return currentPlayingSongList;
	}

	/**
	 * Adds*/
	public static void setCurrentPlayingSongList(SongList songList) {
		currentPlayingSongList.add(songList.get());
	}
	
	public static void addToQueue(Song song) {
		userQueue.add(song);
	}
	
	public static void addToQueue(Album album) {
		userQueue.add(album);
	}
	
	public static Queue getUserQueue() {
		return userQueue;
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
		if(mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
			Player.stop();
		}
		Media currentSongMedia = new Media(new File(song.getDirectory()).toURI().toString());
		if(currentPlayingSong != null) {
			lastlyPlayedSongs.add(currentPlayingSong);
		}
		currentPlayingSong = song;
		mediaPlayer = new MediaPlayer(currentSongMedia);
	}
	
	public static void shuffle(boolean state) {
		Queue tmp = currentPlayingSongList;
		if(state) {
			tmp = currentPlayingSongList;
			currentPlayingSongList.shuffle();
		}
		else {
			if(tmp != null) {
				currentPlayingSongList = tmp;
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
	
	public static void stop() {
		mediaPlayer.stop();
		currentPlayingSong.setPlaying(false);
	}
	
	public static Song next() {
		if(userQueue.isEmpty()) {
			return currentPlayingSongList.getNext(currentPlayingSong);
		}
		else {
			return userQueue.popFront();
		}
	}
	
	public static Song prev() {
		if(lastlyPlayedSongs.front() != currentPlayingSong) {
			return lastlyPlayedSongs.popBack();
		}
		else {
			return currentPlayingSong;
		}
	}

	public static void mute() {
		mediaPlayer.setMute(true);
	}
	
	public static void unMute() {
		mediaPlayer.setMute(false);
	}
	
	public static Status getStatus() {
		return mediaPlayer.getStatus();
	}
	
	public static ReadOnlyObjectProperty<Duration> currentTimeProperty(){
		return mediaPlayer.currentTimeProperty();
	}
	
	public static void seek(Duration duration) {
		mediaPlayer.seek(duration);
	}
}
