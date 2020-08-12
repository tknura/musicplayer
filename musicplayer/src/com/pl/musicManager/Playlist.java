package com.pl.musicManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist extends MusicStructure{
	
	private List<Integer> shuffleTmpList;
	
	public Playlist(String title) {
		super(title);
		shuffleTmpList = new ArrayList<Integer>();
	}

	public Playlist(String title, List<Integer> songs) {
		super(title, songs);
	}

	public Song popFront() {
		return super.remove(super.front());	
	}
	
	/*
	 * Method which return next song in queue if it exists
	 */
	public Song getNext(Song song) {
		if(super.indexOf(song) + 1 <= super.size() - 1) {
			return super.getSongWithIndex(super.indexOf(song) + 1);
		}
		else {
			return null;
		}
	}
	
	public Song getPrev(Song song) {
		if (super.indexOf(song) - 1 >= 0) {
			return getSongWithIndex(indexOf(song) - 1);
		}
		return null;
	}
	
	/*
	 * Method which shuffles queue
	 */
	public void shuffle() {
		shuffleTmpList = songs;
		Collections.shuffle(songs);
	}
	
	public void deshuffle() { 
		songs = shuffleTmpList;
	}
	
}
