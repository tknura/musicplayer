package com.pl.musicManager;


import java.util.Collections;

public class Queue extends SongList{
	
	public Queue() {
		super("");
	}
	
	/*
	 * Method which adds whole album to the queue
	 */
	public void add(Album album) {
		super.add(album.get());
	}
	
	/*
	 * Method which pops front element of the list and return him
	 */
	public Song popBack() {
		return super.remove(super.size() - 1);
	}
	
	/*
	 * Method which pops front element of the list and return him
	 */
	public Song popFront() {
		Song result = super.front();
		super.remove(0);
		return result;
	}
	
	/*
	 * Method which return lastly added song to queue
	 */
	public Song getLastlyAdded() {
		return super.getSong(super.size() - 1);
	}
	
	/*
	 * Method which return index of song passed in the argument
	 */
	public Song getNext(Song song) {
		if(super.get().indexOf(song) + 1 <= super.get().size() - 1) {
			return super.getSong(super.indexOf(song) + 1);
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
