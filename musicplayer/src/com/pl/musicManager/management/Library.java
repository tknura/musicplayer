package com.pl.musicManager.management;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;
import com.pl.musicManager.Album;
import com.pl.musicManager.Artist;
import com.pl.musicManager.Playlist;
import com.pl.musicManager.SongList;
  
public class Library {

	private static SongList songList;
	private static List<Album> albumList;
	private static List<Artist> artistList;
	private static List<Playlist> playlistList;
	
	static {
	
		
		
	}
	
	public SongList getSongList() {
		return songList;
	}
	public List<Album> getAlbumList() {
		return albumList;
	}
	public List<Artist> getArtistList() {
		return artistList;
	}
	public List<Playlist> getPlaylistList() {
		return playlistList;
	}

	
	public void writeToJSON()  {
		try {
			//Creating file inside resources
			Path source = Paths.get("src/resources");
			
			FileWriter fileWriter = new FileWriter(source.toAbsolutePath().toString() + "/library.json");
			
			//Initializing gson
			Gson gson = FxGson.coreBuilder().setPrettyPrinting().create();
			
			//Saving songList
			gson.toJson(songList, fileWriter);
			//Saving albumList
			gson.toJson(new AlbumListWrapper(Library.albumList), fileWriter);
			//Saving artistList
			gson.toJson(new ArtistListWrapper(Library.artistList), fileWriter);
			//Saving playlistList
			gson.toJson(new PlaylistListWrapper(Library.playlistList),fileWriter);
			
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private class AlbumListWrapper{
		List<Album> albumList;
		private AlbumListWrapper(List<Album> albumList) {
			this.albumList = albumList;
		}
	}
	
	private class ArtistListWrapper{
		List<Artist> artistList;
		private ArtistListWrapper(List<Artist> artistList) {
			this.artistList = artistList;
		}
	}
	
	private class PlaylistListWrapper{
		List<Playlist> playlistList;
		private PlaylistListWrapper(List<Playlist> playlistList) {
			this.playlistList = playlistList;
		}
	}
}
