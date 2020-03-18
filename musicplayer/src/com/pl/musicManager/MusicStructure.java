package com.pl.musicManager;

import java.util.ArrayList;
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

	public List<Integer> getSongs(){
		return songs;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void add(Song song) {
		songs.add(song.getId());
	}
	
	public void add(List<Song> obj) {
		for(Song song : obj) {
			add(song);
		}
	}
	
	public void remove(Song song) {
		songs.remove(song);
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
	
	public void removeID(int songID) {
		for(int song : songs) {
			if(song == songID) {
				songs.remove(song);
			}
		}
	}
	
	public void print() {
		int i = 1;
		System.out.println(title + ":");
		List<Song> temp = Library.getSongs(songs);
		for(Song song : temp) {
			song.print();
		}
	}
}
