package com.pl.musicManager.management;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.pl.musicManager.Album;
import com.pl.musicManager.Artist;
import com.pl.musicManager.Playlist;
import com.pl.musicManager.Song;
import com.pl.musicManager.SongList;
import com.pl.utility.Logger;
import com.pl.utility.json.AlbumJsonHandler;
import com.pl.utility.json.JsonHandler;
import com.pl.utility.json.PlaylistJsonHandler;
import com.pl.utility.json.SongListJsonHandler;
  
public class Library {

	private static File libFile;
	private static SongList songList;
	private static List<Album> albumList;
	private static List<Artist> artistList;
	private static List<Playlist> playlistList;
	
	static {
		libFile = new File(Paths.get("src/resources" + "/library.json").toString());
		initializeLibrary();
		
		if(albumList == null) {
			albumList = new LinkedList<Album>();
		}
		if(artistList == null) {
			artistList = new LinkedList<Artist>();
		}
		if(playlistList == null) {
			playlistList = new LinkedList<Playlist>();
		}
	}
	
	
	public static SongList getSongList() {
		return songList;
	}
	public static List<Album> getAlbumList() {
		return albumList;
	}
	public static List<Artist> getArtistCollections() {
		return artistList;
	}
	public static List<Playlist> getPlaylistList() {
		return playlistList;
	}
	
	/**
	 * Method initializes Library with library.json contents if the file exists. 
	 * Otherwise, it explores specified folders from Config class in search for songs.
	 * */
	public static void initializeLibrary() {
		if(libFile.exists()) {
			System.out.println("Library file found, loading contents");
			 load();
		}else {
			System.out.println("File has not been found, creating library");
			songList = new SongList("main.songlist", retrieveSongs());	
			albumList = songList.retrieveAlbums();
		}
	
	}
		
	/** 
	 * method parses library.json file into valid songList*/
	private static void load() {	
		try {
			Reader reader = new FileReader(libFile);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			Type mapType = new TypeToken<Map<String, JsonElement>>(){}.getType();
			Map<String, JsonElement> map = gson.fromJson(reader, mapType);
			
			for(Map.Entry<String, JsonElement> entry : map.entrySet()) {
				Logger.debug(entry.getKey() + " found");
				if(entry.getKey().equals("songlist")) {
					songList = parse(entry.getValue(), SongList.class);
				}else if(entry.getKey().equals("playlistList")) {
					Type type = new TypeToken<List<Playlist>>() {}.getType();
					playlistList = parseCollection(entry.getValue(), type);
					
				}else if(entry.getKey().equals("albumList")) {
					Type type = new TypeToken<List<Album>>() {}.getType();
					albumList = parseCollection(entry.getValue(), type);
				}
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/** 
	 * Method explores directories,
	 * parses files as songs,
	 * returns list of songs. */
	private static List<Song> retrieveSongs() {
		return FileProcessor.getSongList();
	}
	
	/**
	 * Method saves songlist, playlists, albums and artists into library.json file*/
	public static void saveLibrary() {
		if(songList == null || songList.isEmpty()) {
			return;
		}
		try {
			//Creating file inside resources
			FileWriter fileWriter = new FileWriter(libFile.getAbsolutePath());
			Gson gson = FxGson.coreBuilder().setPrettyPrinting().create();
			JsonObject obj = new JsonObject();
			
			//Songlist - songs from songlist are serialized in default way
			obj.add("songlist", parse(songList));
			
//			//Playlists
			obj.add("playlistList", parse(playlistList));
			
			//Albums
			obj.add("albumList", parse(albumList));
			
			
			gson.toJson(obj, fileWriter);
			fileWriter.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private static <T> JsonHandler resolveJsonHandler(T obj) {
		if(obj instanceof SongList) {
			Logger.debug("Songlist handler");
			return new SongListJsonHandler();
		}else if(obj instanceof Playlist) {
			Logger.debug("Playlist handler");
			return new PlaylistJsonHandler();
		}else if(obj instanceof Album){
			Logger.debug("Playlist handler");
			return new AlbumJsonHandler();
		};
		Logger.debug("Not resolved!");
		return null;
	}
	
	
	private static JsonHandler resolveJsonHandler(Type type) {
		Logger.debug(type.toString());
		Type albumType = new TypeToken<List<Album>>() {}.getType();
		Type playlistType = new TypeToken<List<Playlist>>() {}.getType();
		if(type.equals(SongList.class)) {
			return new SongListJsonHandler();
		}else if(type.equals(albumType)) {
			return new AlbumJsonHandler();
		}else if(type.equals(playlistType)) {
			return new PlaylistJsonHandler();
		}
		
		Logger.debug("Collection type not resolved");
		return null;
	}
	
	private static <T> JsonElement parse(T src) {
		if(src != null) {
			JsonHandler handler = resolveJsonHandler(src);
			if(handler != null) {
				JsonElement parseResult = handler.parseToJson(src);
				return parseResult;
			}
		}
		return null;
	}	
	
	private static <T> T parse(JsonElement src, Type type) {
		if(src != null) {
			JsonHandler handler = resolveJsonHandler(type);
			if(handler != null) {
				T parseResult = handler.parseFromJson(src, type);
				return parseResult;
			}
		}
		return null;
	}
	
	private static <T> List<T> parseCollection(JsonElement src, Type type){
		if(src != null) {
			JsonHandler handler = resolveJsonHandler(type);
			
			if(handler != null) {
				List<T> parseResult = (List<T>) handler.parseCollectionFromJson(src, type);
				return parseResult;
			}
		}
		
		return null;
	}
	
	private static <T> JsonElement parse(List<T> src) {
		if(src != null && !src.isEmpty()) {
			T item = src.get(0);
			JsonHandler handler = resolveJsonHandler(item);
			return handler.parseCollectionToJson(src);
		}else {
			return null;
		}
	}
	
	public static List<Song> getSongs(List<Integer> ids) {
		return songList.get(ids);
	}

	public static void addAlbum(Album album) {
		albumList.add(album);
	}
	
	public static void addPlaylist(Playlist playlist) {
		playlistList.add(playlist);
	}
	
	public static void addArtist(Artist artist) {
		artistList.add(artist);
	}
	
	public static void synchronize() {
		songList.synchronize();
	}
}



