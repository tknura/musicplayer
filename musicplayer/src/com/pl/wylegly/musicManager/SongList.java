package com.pl.wylegly.musicManager;

import java.util.LinkedList;

public class SongList {
	protected LinkedList<Song> songs;
	
	public LinkedList<Song> get() {
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
		//TODO sorting alphabetical by song name
	}
	
	public void sortByAlbum() {
		//TODO sorting alphabetical by album name
	}
	
	public void sortByArists() {
		//TODO sorting alphabetical by arists name
	}
	
}
