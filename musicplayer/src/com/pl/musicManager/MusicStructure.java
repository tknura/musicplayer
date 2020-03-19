package com.pl.musicManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.pl.musicManager.management.Library;

public abstract class MusicStructure {
	
	protected List<Integer> songs;
	protected String title;
	
	public MusicStructure(String title) {
		this(title, new ArrayList<Integer>());
	}
	
	public MusicStructure(String title, List<Integer> songs) {
		this.title = title;
		this.songs = songs;
	}

	public List<Integer> getSongsIDs(){
		return songs;
	}
	
	public List<Song> getSongs(){
		List<Song> list = new LinkedList<Song>();
		SongList songlist = Library.getSongList();
		for(int id : songs) {
			list.add(songlist.getSongWithID(id));
		}
		return list;
	}
	
	public String getTitle() {
		return title;
	}
	
	/**
	 * Method returns song located under specified index
	 * */
	public Song getSongWithIndex(int index) {
		if(index >= 0) {
			SongList songlist = Library.getSongList();
			if(songlist != null && songs != null) {
				if(index < songs.size()) {
					//Retrieving song id
					int requestedSongID = songs.get(index);
					return songlist.getSongWithID(requestedSongID);
				}
			}
		}
		return null;
	}
	
	
	/**
	 * Method retrieves song with specified song id
	 * */
	public Song getSongWithID(int id) {
		return getSongWithIndex(songs.indexOf(id));
	}
	
	/**
	 * Method retrieves first song of the Music Structure
	 * */
	public Song front() {
		return this.getSongWithIndex(0);
	}
	
	
	/**
	 * Method retrieves last song of the Music Structure
	 * */
	public Song back() {
		return this.getSongWithIndex(this.size());
	}

	
	
	public void add(Song song) {
		songs.add(song.getId());
	}
	
	public void add(List<Song> obj) {
		for(Song song : obj) {
			add(song);
		}
	}
	
	public void add(MusicStructure structure) {
		for(int id : structure.songs) {
			songs.add(id);
		}
	}
		
	public Song remove(Song song) {
		songs.remove(song.getId());
		return song;
	}
	
	
	public void removeID(int songID) {
		for(int song : songs) {
			if(song == songID) {
				songs.remove(song);
			}
		}
	}
	

	public void synchronize() {
		SongList sl = Library.getSongList();
		if(sl != null) {
			for(int id : songs) {
				if(!sl.contains(id)){
					songs.remove(id);
				}
			}
		}
		
	}

	
	public void print() {
		System.out.println(title + ":");
		List<Song> temp = Library.getSongs(songs);
		for(Song song : temp) {
			song.print();
		}
	}
	
	public int size() {
		return songs.size();
	}
	
	public int indexOf(Song song) {
		return songs.indexOf(song.getId());
	}
	
	public boolean isEmpty() {
		return songs.isEmpty();
	}
	

}
