package com.pl.musicManager;


import java.util.Collections;

public class Queue extends SongList{
	
	public Queue() {
		super();
	}
	
	public void shuffle() {
		Collections.shuffle(songs);
	}
}
