package com.pl.musicManager.management;

import java.util.LinkedList;

import com.pl.musicManager.Album;
import com.pl.musicManager.Artist;
import com.pl.musicManager.Playlist;
import com.pl.musicManager.SongList;
  
public class Library {

	private SongList songList;
	private LinkedList<Album> albumList;
	private LinkedList<Artist> artistList;
	private LinkedList<Playlist> playlistList;
	
	public SongList getSongList() {
		return songList;
	}
	public LinkedList<Album> getAlbumList() {
		return albumList;
	}
	public LinkedList<Artist> getArtistList() {
		return artistList;
	}
	public LinkedList<Playlist> getPlaylistList() {
		return playlistList;
	}
	
	public void retrieveSongList() {
		this.songList = new SongList();
	}
}
