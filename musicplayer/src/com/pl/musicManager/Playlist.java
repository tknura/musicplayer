package com.pl.musicManager;

import java.util.Comparator;

public class Playlist extends SongList implements Comparable<Playlist>{
	
	private String name;

	public Playlist(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void sortByAlbums() {
		Comparator<Song> compareByAlbum = (Song s1, Song s2) ->  s1.getAlbum().toString().compareTo(s2.getAlbum().toString());
		songs.sort(compareByAlbum);
	}
	
	public void sortByArists() {
		Comparator<Song> compareByArtists = (Song s1, Song s2) -> s1.getArtist().toString().compareTo(s2.getArtist().toString());
		this.songs.sort(compareByArtists);
	}

	@Override
	public int compareTo(Playlist playlist) {
		return this.name.compareTo(playlist.getName());
	}
	
}
