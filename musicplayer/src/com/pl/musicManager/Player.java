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
	private static Queue mainQueue;
	private static Queue userQueue;
	private static Playlist recentlyPlayedSongs;
	private static Song currentPlayingSong;
	
	static {
		mainQueue = new Queue();
		userQueue = new Queue();
		recentlyPlayedSongs = new Playlist("Recently Played");
	}
	
	// GETERS AND SETTERS
	public static MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
	
	public static Queue getMainQueue() {
		return mainQueue;
	}

	public static void setMainQueue(MusicStructure struct) {
	
		mainQueue.songs.clear();
		mainQueue.add(struct);
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

	public static Playlist getRecentlyPlayedSongs() {
		return recentlyPlayedSongs;
	}

	public static void setRecentlyPlayedSongs(Playlist lastlyPlayedSongs) {
		Player.recentlyPlayedSongs = lastlyPlayedSongs;
	}
	
	public static Song getCurrentPlayingSong() {
		return currentPlayingSong;
	}

	public static void setCurrentPlayingSong(Song currentPlayingSong) {
		Player.currentPlayingSong = currentPlayingSong;
	}
	// -------------------------------------------------------------------------
	
	/*
	 *	Method for loading passed song to mediaPlayer 
	 */
	public static void load(Song song) {
		if(mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
			Player.stop();
		}
		Media currentSongMedia = new Media(new File(song.getDirectory()).toURI().toString());
		if(currentPlayingSong != null) {
			recentlyPlayedSongs.add(currentPlayingSong);
		}
		currentPlayingSong = song;
		mediaPlayer = new MediaPlayer(currentSongMedia);
	}
	
	/*
	 *	Method for shuffling main queue
	 */
	public static void shuffle(boolean state) {
		Queue tmp = mainQueue;
		if(state) {
			tmp = mainQueue;
			mainQueue.shuffle();
		}
		else {
			if(tmp != null) {
				mainQueue = tmp;
			}
		}
	}
	
	/*
	 *	Method for enabling/disabling repeat
	 */
	public static void repeat(boolean state) {
		if(state) {
			mediaPlayer.setCycleCount(Integer.MAX_VALUE);
		}
		else {
			mediaPlayer.setCycleCount(1);
		}
	}
	
	/*
	 * Method which resume playing function
	 */
	public static void resume() {
		mediaPlayer.play();	
		currentPlayingSong.setPlaying(true);
	}
	
	/*
	 * Method which  pause playing function
	 */
	public static void pause() {
		mediaPlayer.pause();
		currentPlayingSong.setPlaying(false);
	}
	
	/*
	 * Method which stop playing function
	 */
	public static void stop() {
		mediaPlayer.stop();
		currentPlayingSong.setPlaying(false);
	}
	
	/*
	 * Method which return next song depending on queues states
	 */
	public static Song next() {
		if(userQueue.isEmpty()) {
			return mainQueue.getNext(currentPlayingSong);
		}
		else {
			return userQueue.popFront();
		}
	}
	
	/*
	 * Method which return previous song depending on queues states
	 */
	public static Song prev() {
		if(recentlyPlayedSongs.front() != currentPlayingSong) {
			System.out.println("lol");
			return recentlyPlayedSongs.popBack();
		}
		else {
			return currentPlayingSong;
		}
	}

	/*
	 *	Method for enabling/disabling mute
	 */
	public static void mute(boolean state) {
		if(state) {
			mute();
		}
		else {
			unmute();
		}
	}
	
	/*
	 *	Method which mutes the player
	 */
	public static void mute() {
		mediaPlayer.setMute(true);
	}
	
	/*
	 *	Method which unmutes the player
	 */
	public static void unmute() {
		mediaPlayer.setMute(false);
	}
	
	/*
	 *	Method which return musicplayer's status
	 */
	public static Status getStatus() {
		return mediaPlayer.getStatus();
	}
	
	/*
	 *	Method which return musicplayer's time property
	 */
	public static ReadOnlyObjectProperty<Duration> currentTimeProperty(){
		return mediaPlayer.currentTimeProperty();
	}
	
	/*
	 *	Method which seeks the player to passed duration
	 */
	public static void seek(Duration duration) {
		mediaPlayer.seek(duration);
	}
}
