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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.pl.musicManager.Album;
import com.pl.musicManager.Artist;
import com.pl.musicManager.Playlist;
import com.pl.musicManager.Song;
import com.pl.musicManager.SongList;
  
public class Library {

	private static SongList songList;
	private static List<Album> albumList;
	private static List<Artist> artistList;
	private static List<Playlist> playlistList;
	
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
	/**
	 * Method checks if library.json exists
	 * If it does, it reads its contents, and initializes music structures
	 * Or creates the file, exploring specified folders in Config class*/
	public static void initializeLibrary() {
		
		File libFile = new File(Paths.get("src/resources" + "/library.json").toString());
		if(libFile.exists()) {
			 load(libFile);
		}else {
			System.out.println("File has not been found, creating library");
			songList = new SongList(retrieveSongs());	
		}
	}
	
	/** 
	 * method parses library.json file into valid songList*/
	private static void load(File libFile) {
		Gson gson = new Gson();
	}
	
	/** 
	 * method explores directories,
	 * parses files as songs
	 * return List of Songs needed to create SongList */
	private static List<Song> retrieveSongs() {
		return FileProcessor.getSongList();
	}
	
	/**
	 * Method creates library.json file
	 * saves song list.
	 * Method is being used in 2 scenarios:
	 * when library does not exist
	 * or when application is being closed*/
	private static void saveSongList()  {
		try {
			
			//Creating file inside resources
			Path source = Paths.get("src/resources");
			FileWriter fileWriter = new FileWriter(source.toAbsolutePath().toString() + "/library.json");
			
			
			//GSON used for default serialization
			Gson gson = FxGson.coreBuilder().setPrettyPrinting().create();
			System.out.println("halo");
			System.out.println(songList.get().size());
			//Saving songList
			gson.toJson(songList, fileWriter);
			
			
			//GSON used for custom serialization of containers
		
			
//			JsonSerializer <List<SongList>> serializer = new JsonSerializer<List<SongList>>() {
//				@Override
//				public JsonElement serializer(List<SongList> src, Type typeOfSrc, JsonSerializationContext context) {
//					JsonObject jsonSongList = new JsonObject();
//					
//					List<Integer> SongsIds = new LinkedList<>();
//					for(Song song : src) {
//						
//					}
//					
//				}
//			}
//			//Saving albumList
//			customGson.toJson(new AlbumListWrapper(Library.albumList), fileWriter);
//			//Saving artistList
//			customGson.toJson(new ArtistListWrapper(Library.artistList), fileWriter);
//			//Saving playlistList
//			customGson.toJson(new PlaylistListWrapper(Library.playlistList),fileWriter);
			
			
			
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void saveLibrary() {
		saveSongList();
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
