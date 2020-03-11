package com.pl.musicManager;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.pl.utility.Logger;

import javafx.beans.property.SimpleStringProperty;

public class SongList {
	protected SimpleStringProperty title;
	protected List<Song> songs;
	
	public SongList(String title) {
		
		this(title, new LinkedList<Song>());
		Logger.debug("Default linked list");
	}
	
	public SongList(String title, List<Song> songs) {
		this.title = new SimpleStringProperty(title);
		this.songs = songs;
		Logger.debug("Songs: " + songs);
	}
	
	public List<Song> get() {
		return songs;
	}
	
	public String getTitle() {
		return title.get();
	}

	public void setName(String title) {
		this.title = new SimpleStringProperty(title);
	}
	
	public void add(Song song) {
		songs.add(song);
	}
	
	public void add(LinkedList<Song> songList) {
		songs.addAll(songList);
	}
	
	public void remove(Song song) {
		songs.remove(song);
	}
	
	public void remove(int index) {
		songs.remove(index);
	}
	
	public void load() {
		//TODO loading from JSON file
	}
	
	public void save() {
		//TODO saving to JSON file
	}
	
	public void sortByName() {
		Collections.sort(songs);
	}
	
	public void print() {
		int i = 1;
		for(Song song : songs) {
			System.out.println("Song #" + i++ + ": ");
			song.print();
		}
	}
	
	public boolean isEmpty() {
		return songs.isEmpty();
	}

	
}
