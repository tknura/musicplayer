package com.pl.musicManager;

import java.io.File;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class Player{
	
	private static MediaPlayer mediaPlayer;
	//queue where user puts his song demands
	private static Queue userQueue;
	//playlist which Player uses to move forward and backward while playing
	private static Playlist currentPlayingList;
	private static Song currentPlayingSong;
	
	//playlist with last 50 played songs
	private static Playlist recentlyPlayedSongs;

	static {
		userQueue = new Queue();
		currentPlayingList = new Playlist("Now Playing");
		recentlyPlayedSongs = new Playlist("Recently Played");
	}
	
	// GETERS AND SETTERS ----------------------------------------------
	
	public static MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
	
	public static Playlist getCurrentPlayingList() {
		return currentPlayingList;
	}
	
	public static void setCurrentPlayingList(MusicStructure struct) {
		currentPlayingList.songs.clear();
		currentPlayingList.add(struct);
	}
	
	public static void addToQueue(Song song) {
		userQueue.add(song);
	}
	
	public static void addToQueue(MusicStructure struct) {
		userQueue.add(struct);
	}

	public static Playlist getRecentlyPlayedSongs() {
		return recentlyPlayedSongs;
	}
	
	public static Song getCurrentPlayingSong() {
		return currentPlayingSong;
	}
	
	// -------------------------------------------------------------------------
	
	/*
	 *	Method for loading passed song to mediaPlayer 
	 */
	public static void load(Song song) {
		//check if 
		if(mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
			Player.stop();
		}
		//create media
		Media currentSongMedia = new Media(new File(song.getDirectory()).toURI().toString());
		//check if there was playing something
		if(currentPlayingSong != null) {
			if(recentlyPlayedSongs.size() > 50) {
				recentlyPlayedSongs.popFront();
			}
			currentPlayingSong.played();
		}
		currentPlayingSong = song;
		//load media
		mediaPlayer = new MediaPlayer(currentSongMedia);
	}
	
	/*
	 *	Method for shuffling main queue
	 */
	public static void shuffle(boolean state) {
		if(state) {
			currentPlayingList.shuffle();
		}
		else {
			currentPlayingList.deshuffle();
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
			return currentPlayingList.getNext(currentPlayingSong);
		}
		else {
			return userQueue.popFront();
		}
	}
	
	/*
	 * Method which return previous song in current playing list
	 */
	public static Song prev() {
		Song result;
		if((result = currentPlayingList.getPrev(currentPlayingSong)) != null) {
			return result;
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
