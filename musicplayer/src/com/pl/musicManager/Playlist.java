package com.pl.musicManager;

import java.util.Comparator;
import java.util.List;

import com.pl.musicManager.management.Library;

public class Playlist extends MusicStructure{
	

	public Playlist(String title) {
		super(title);
	}

	public Playlist(String title, List<Integer> songs) {
		super(title, songs);
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
