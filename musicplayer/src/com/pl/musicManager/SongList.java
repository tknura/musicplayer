package com.pl.musicManager;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.pl.musicManager.management.FileProcessor;

public class SongList {
	protected LinkedList<Song> songs;
	
	public SongList() {
		songs = new LinkedList<Song>();
	}
	
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
	
	
}
