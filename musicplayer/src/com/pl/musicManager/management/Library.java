package com.pl.musicManager.management;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.pl.musicManager.Album;
import com.pl.musicManager.Artist;
import com.pl.musicManager.Playlist;
import com.pl.musicManager.Song;
import com.pl.musicManager.SongList;
import com.pl.utility.JsonHelper;
import com.pl.utility.Logger;
  
public class Library {

	private static File libFile;
	private static SongList songList;
	private static List<Album> albumList;
	private static List<Artist> artistList;
	private static List<Playlist> playlistList;
	
	private static JsonHelper jsonHelper;
	
	static {
		libFile = new File(Paths.get("src/resources" + "/library.json").toString());
		songList = new SongList("main.songlist");
		albumList = new LinkedList<Album>();
		artistList = new LinkedList<Artist>();
		playlistList = new LinkedList<Playlist>();
		jsonHelper = new JsonHelper();
		//initializeLibrary();
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
		}
	
	}
		
	/** 
	 * method parses library.json file into valid songList*/
	private static void load() {	
		
		try {
			
			JsonDeserializer<SongList> songListDeserializer = jsonHelper.getSongListDeserializer();
			JsonDeserializer<Playlist> playlistDeserializer = jsonHelper.getPlaylistDeserializer();
			
			GsonBuilder customGsonBuilder = new GsonBuilder();
			
			customGsonBuilder.registerTypeAdapter(SongList.class, songListDeserializer);
			customGsonBuilder.registerTypeAdapter(Playlist.class, playlistDeserializer);
			
			Gson gson = customGsonBuilder.setPrettyPrinting().create();
			
			FileReader reader = new FileReader(libFile.getAbsolutePath());
			
			JsonObject json = gson.fromJson(reader, JsonObject.class);
			
			HashMap<String,JsonElement> map = new HashMap<String, JsonElement>();
			Type mapType = new TypeToken<HashMap<String, JsonElement>>(){}.getType();
			map = gson.fromJson(json, mapType);
						
			for(Entry<String, JsonElement> entry : json.entrySet()) {
				
				if(entry.getKey().equals("songlist")) {
					songList = gson.fromJson(entry.getValue(), SongList.class);
				}else if(entry.getKey().equals("playlistList")) {
					
					Type listType = new TypeToken<List<Playlist>>() {}.getType();
					playlistList = gson.fromJson(entry.getValue(), listType);
					
				}
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		if(songList.isEmpty()) {
			return;
		}
		try {
			//Creating file inside resources
			FileWriter fileWriter = new FileWriter(libFile.getAbsolutePath());
			Gson gson = FxGson.coreBuilder().setPrettyPrinting().create();
			
			JsonObject obj = new JsonObject();
			
			//Songlist - songs from songlist are serialized in default way
			obj.add("songlist", gson.toJsonTree(songList));
			
			//Playlists
			obj.add("playlistList", parseSongList(playlistList));
			
			//Albums
			obj.add("albumList", parseSongList(albumList));
			
			//Artists
			obj.add("artistList", parseArtistList(artistList));
			
			gson.toJson(obj, fileWriter);
			fileWriter.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Method for Artists serialization. 
	 * Each Artist object is represented by array of album id's.
	 * */
	private static JsonElement parseArtistList(List<Artist> list) {
		
		JsonSerializer<Artist> artistSerializer = jsonHelper.getArtistSerializer();
		
		JsonObject obj = new JsonObject();
		
		GsonBuilder customGsonBuilder = new GsonBuilder();
		customGsonBuilder.registerTypeAdapter(Artist.class, artistSerializer);
		
		Gson gson = customGsonBuilder.setPrettyPrinting().create();
		
		Type type = new TypeToken<List<Artist>>() {}.getType();
		return gson.toJsonTree(list, type);
	}
		
	/**
	 * Method used to parse playlists, albums, using songs id's only.
	 */
	private static <T extends SongList> JsonElement parseSongList(List<T> list) {
		
		GsonBuilder customGsonBuilder = new GsonBuilder();
		
		JsonSerializer <SongList> playlistSerializer = jsonHelper.getSonglistSerializer();
		JsonSerializer <Album> albumSerializer = jsonHelper.getAlbumSerializer();
		
		//Selecting type adapter
		if(!list.isEmpty()) {
			SongList tmp = list.get(0);
			if(tmp instanceof Playlist) {
				customGsonBuilder.registerTypeAdapter(Playlist.class, playlistSerializer);
			}else if(tmp instanceof Album) {
				customGsonBuilder.registerTypeAdapter(Album.class, albumSerializer);
			}
		}else {
			return null;
		}
		
		Gson customGson = customGsonBuilder.setPrettyPrinting().create();
				
		return customGson.toJsonTree(list);
		
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
	
}



