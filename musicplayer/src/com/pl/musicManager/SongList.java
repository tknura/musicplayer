package com.pl.musicManager;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SongList {
	protected List<Song> songs;
	
	public SongList() {
		songs = new LinkedList<Song>();
	}
	
	public SongList(List<Song> songs) {
		this.songs = songs;
	}
	
	public List<Song> get() {
		return songs;
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
	
}
