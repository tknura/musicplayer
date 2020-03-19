package com.pl.musicManager;


import java.util.Collections;

public class Queue extends MusicStructure{
	
	public Queue() {
		super("");
	}
	
	/*
	 * Method which adds whole album to the queue
	 */
	public void add(Album album) {
		super.add(album);
	}
	
	
	/*
	 * Method which pops front element of the list and return him
	 */
	public Song popBack() {
		return super.remove(super.back());	
	}
	
	/*
	 * Method which pops front element of the list and return him
	 */
	public Song popFront() {
		return super.remove(super.front());
	}
	
	/*
	 * Method which return lastly added song to queue
	 */
	public Song getLastlyAdded() {
		return super.getSongWithID(super.size() - 1);
	}
	
	/*
	 * Method which return index of song passed in the argument
	 */
	public Song getNext(Song song) {
		if(super.indexOf(song) + 1 <= super.size() - 1) {
			return super.getSongWithID(super.indexOf(song) + 1);
		}
		else {
			return null;
		}
	}
	
	/*
	 * Method which shuffles queue
	 */
	public void shuffle() {
		Collections.shuffle(songs);
	}
}
