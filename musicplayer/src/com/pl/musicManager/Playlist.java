package com.pl.musicManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.pl.musicManager.management.Library;

public class Playlist extends MusicStructure{
	
	private List<Integer> shuffleTmpList;
	
	public Playlist(String title) {
		super(title);
		shuffleTmpList = new ArrayList<Integer>();
	}

	public Playlist(String title, List<Integer> songs) {
		super(title, songs);
	}

	public Song popFront() {
		return super.remove(super.front());	
	}
	
	/*
	 * Method which return next song in queue if it exists
	 */
	public Song getNext(Song song) {
		if(super.indexOf(song) + 1 <= super.size() - 1) {
			return super.getSongWithID(super.indexOf(song) + 1);
		}
		else {
			return null;
		}
	}
	
	public Song getPrev(Song song) {
		if (super.indexOf(song) - 1 >= 0) {
			return getSongWithIndex(indexOf(song) - 1);
		}
		return null;
	}
	
	/*
	 * Method which shuffles queue
	 */
	public void shuffle() {
		shuffleTmpList = songs;
		Collections.shuffle(songs);
	}
	
	public void deshuffle() { 
		songs = shuffleTmpList;
	}
	
//	public void sortByAlbums() {
//		Comparator<Song> compareByAlbum = (Song s1, Song s2) ->  s1.getAlbum().toString().compareTo(s2.getAlbum().toString());
//		songs.sort(compareByAlbum);
//	}
	
//	public void sortByArists() {
//		Comparator<Song> compareByArtists = (Song s1, Song s2) -> s1.getArtist().toString().compareTo(s2.getArtist().toString());
//		this.songs.sort(compareByArtists);
//	}

//	@Override
//	public int compareTo(Playlist playlist) {
//		return this.getTitle().compareTo(playlist.getTitle());
//	}
	
}
