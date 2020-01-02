package com.pl.musicManager;

import java.io.File;
import java.util.Collections;
import java.util.List;

import com.pl.musicManager.management.FileProcessor;

public class Queue extends SongList{
	
	public Queue() {
		super();
	}
	
	public void shuffle() {
		Collections.shuffle(songs);
	}
}
