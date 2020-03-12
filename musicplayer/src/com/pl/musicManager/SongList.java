package com.pl.musicManager;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;

public class SongList {
	protected SimpleStringProperty title;
	protected List<Song> songs;
	
	public SongList(String title) {
		this(title, new LinkedList<Song>());
	}
	
	public SongList(String title, List<Song> songs) {
		this.title = new SimpleStringProperty(title);
		this.songs = songs;
	}
	
	public List<Song> get() {
		return songs;
	}
	
	public Song back() {
		return songs.get(songs.size() - 1);
	}
	
	public Song front() {
		return songs.get(0);
	}
	
	public Song getSong(int index) {
		return songs.get(index);
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
	
	public void add(List<Song> songList) {
		songs.addAll(songList);
	}
	
	public void remove(Song song) {
		songs.remove(song);
	}
	
	public Song remove(int index) {
		return songs.remove(index);
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
	
	public boolean isEmpty() {
		return songs.isEmpty();
	}
	
	public int size() {
		return songs.size();
	}
	
	public int indexOf(Song song) {
		return songs.indexOf(song);
	}
	
	public void print() {
		int i = 1;
		for(Song song : songs) {
			System.out.println("Song #" + i++ + ": ");
			song.print();
		}
	}

	
}
