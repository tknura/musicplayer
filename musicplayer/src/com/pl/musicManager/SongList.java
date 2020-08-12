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
			Song tmp = getSongWithIndex(id);
			if(tmp!=null) {
				obj.add(getSongWithIndex(id));
			}
		}
		return obj;
	}
	
	public Song getSongWithIndex(int index) {
		if(songs.size() > index) {
			return songs.get(index);
		}else {
			return null;
		}
	}
	
	public Song getSongWithID(int songID) {
		for(Song song : songs) {
			if(song.getId() == songID) {
				return song;
			}
		}
		return null;
	}
	
	public Song front() {
		return getSongWithIndex(0);
	}
	
	public Song back() {
		return getSongWithIndex(songs.size() - 1);
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
		
		for(Iterator<Song> iterator = songs.iterator(); iterator.hasNext();) {
			Song song = iterator.next();
			File file = new File(song.getDirectory());
			if(!file.exists()) {
				iterator.remove();
			}
		}
	}
	
	public List<Album> retrieveAlbums() {
		Map<String, Album> albums = new HashMap<String, Album>();
		for(Song song : songs) {
			String albumName = song.getAlbum();
			String artistName = albumName.equals("Unknown album") ? 
								"Various artists" : 
								song.getArtist();
						
			Album album = albums.get(albumName);
			if(album == null) {
				int releaseYear = albumName.equals("Unknown album") ?
								  -1 :
								  FileProcessor.retrieveAlbumReleaseYear(new File(song.getDirectory()));
				album = new Album(albumName, artistName, releaseYear, null);
				albums.put(albumName, album);
			}
			album.add(song);
		}

			return new LinkedList<>(albums.values());
	}

	
}
