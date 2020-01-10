package com.pl.musicManager.management;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.LinkedList;

import org.hildan.fxgson.FxGson;
import org.hildan.fxgson.FxGson.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder.*;
import com.pl.musicManager.Album;
import com.pl.musicManager.Artist;
import com.pl.musicManager.Playlist;
import com.pl.musicManager.Song;
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
	
	public void writeToJSON()  {
		try {
			Path source = Paths.get("src/resources");
			FileWriter fileWriter = new FileWriter(source.toAbsolutePath().toString() + "/library.json");
			System.out.println(source.toAbsolutePath().toString());
			
			Gson gson = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();
			
			Song song = new Song("Sunflower", "Post Malone", "Sunflower", Duration.ZERO, 3, "myDirectory");
			gson.toJson(song, fileWriter);
			
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
