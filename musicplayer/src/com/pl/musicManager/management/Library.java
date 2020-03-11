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
import java.util.Scanner;
import java.util.Set;

import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
import com.pl.utility.Logger;
import com.sun.javafx.collections.MappingChange.Map;
  
public class Library {

	private static File libFile;
	private static SongList songList;
	private static List<Album> albumList;
	private static List<Artist> artistList;
	private static List<Playlist> playlistList;
	
	static {
		libFile = new File(Paths.get("src/resources" + "/library.json").toString());
		songList = new SongList("main.songlist");
		albumList = new LinkedList<Album>();
		artistList = new LinkedList<Artist>();
		playlistList = new LinkedList<Playlist>();
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
		System.out.println(libFile.getAbsolutePath());
		if(libFile.exists()) {
			 load();
		}else {
			System.out.println("File has not been found, creating library");
			songList = new SongList("main.songlist", retrieveSongs());	
		}
	
	}
	
	
	/** 
	 * method parses library.json file into valid songList*/
	private static void load() {	
		FileReader fileReader;
		try {
			fileReader = new FileReader(libFile.getAbsolutePath());
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonObject obj = gson.fromJson(fileReader, JsonObject.class); 
			JsonObject sl = (JsonObject) obj.get("songlist");
			//Logger.debug("songlist-read:\n " + gson.toJson(sl));
			
			Logger.debug("songlist adress: " + songList);
			songList = gson.fromJson(gson.toJson(sl), SongList.class);
			
			songList.print();
			try {
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
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
			//obj.add("playlistList", parseSongList(playlistList));
			
			//Albums
			//obj.add("albumList", parseSongList(albumList));
			
			//Artists
			//obj.add("artistList", parseArtistList(artistList));
			
			gson.toJson(obj, fileWriter);
			fileWriter.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Method for Artists serialization. Each Artist object is represented by 
	 * array of album id's.
	 * */
	private static JsonElement parseArtistList(List<Artist> list) {
		
		JsonSerializer <Artist> artistSerializer = new JsonSerializer<Artist>() {
			@Override
			public JsonElement serialize(Artist src, Type type, JsonSerializationContext context) {
				JsonArray ids = new JsonArray();
				for(Album album : src.getAlbums()) {
					ids.add(album.getId());
				}
				return ids;
			}
		};
		
		JsonObject obj = new JsonObject();
		
		GsonBuilder customGsonBuilder = new GsonBuilder();
		customGsonBuilder.registerTypeAdapter(Artist.class, artistSerializer);
		Gson gson = customGsonBuilder.setPrettyPrinting().create();
		
		for(Artist artist : list) {
			obj.add(artist.getName().get(), gson.toJsonTree(artist));
		}
		return obj;
	}
		
	/**
	 * Method used to parse playlists, albums, using songs id's only.
	 */
	private static <T extends SongList> JsonElement parseSongList(List<T> list) {
		
		//Custom serializer for songlist (id only)
		GsonBuilder customGsonBuilder = new GsonBuilder();
		JsonSerializer <SongList> playlistSerializer = new JsonSerializer<SongList>() {
			
			@Override
			public JsonElement serialize(SongList src, Type type, JsonSerializationContext context) {
				Logger.debug("[SERIALIZATION] custom serialization");
				JsonArray ids = new JsonArray();
				for(Song song : src.get()) {
					ids.add(song.getId());
				}
				return ids;
			};
			
		};
		
		//Custom serializer for albums
		JsonSerializer <Album> albumSerializer = new JsonSerializer<Album>() {

			@Override
			public JsonElement serialize(Album src, Type type, JsonSerializationContext context) {
				JsonObject result = new JsonObject();
				result.addProperty("id", src.getId());
				result.addProperty("artist", src.getArtist().get());
				result.addProperty("release.year", src.getReleaseYear());
				result.addProperty("length.in.seconds", src.getLengthInSeconds());
					
				JsonArray ids = (JsonArray) playlistSerializer.serialize(src, type, context);
				result.add("songs", ids);
				return result;
			}
			
		};
		
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
		
		//serializing
		JsonObject songs = new JsonObject();
		for(SongList songlist : list) {
			songs.add(songlist.getTitle(), customGson.toJsonTree(songlist));
		}
		
		return songs;
		
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
