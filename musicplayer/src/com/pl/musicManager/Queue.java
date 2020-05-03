package com.pl.musicManager;

public class Queue extends MusicStructure{
	
	public Queue() {
		super("");
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
	
}
