package com.pl.musicManager;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.pl.musicManager.management.FileProcessor;
import com.pl.utility.Logger;

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
	
	/**
	 * Method returns list of songs, which ids are contained inside list of integers
	 * */
	public List<Song> get(List<Integer> ids){
		List<Song> obj = new LinkedList<Song>();
		for(int id : ids) {
			Song tmp = getSong(id);
			if(tmp!=null) {
				obj.add(getSong(id));
			}
		}
		return obj;
	}
	
	public Song getSong(int index) {
		if(songs.size() >= index) {
			return songs.get(index);
		}else {
			return null;
		}
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
	
	public Song front() {
		return getSong(0);
	}
	
	public int size() {
		return songs.size();
	}
	
	public void add(List<Song> songList) {
		songs.addAll(songList);
	}
	
	public void remove(Song song) {
		songs.remove(song);
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
	
	public boolean contains(int id) {
		for(Song song : songs) {
			if(song.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	public void synchronize() {
		for(Song song : songs) {
			File file = new File(song.getDirectory());
			if(!file.exists()) {
				songs.remove(song);
			}
		}
	}
	
	public List<Album> retrieveAlbums() {
		Map<String, Album> albums = new HashMap<String, Album>();
		for(Song song : songs) {
			
			String albumName = song.getAlbum();
			String artistName = song.getArtist();
			
			Album album = albums.get(albumName);
			if(album == null) {
				int releaseYear = FileProcessor.retrieveAlbumReleaseYear(new File(song.getDirectory()));
				albums.put(albumName, new Album(albumName, artistName, releaseYear, null));
			}else {
				album.add(song);
			}
		}
		return null;
	}

	
}
