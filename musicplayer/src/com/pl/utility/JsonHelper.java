package com.pl.utility;

import java.io.File;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pl.musicManager.Album;
import com.pl.musicManager.Artist;
import com.pl.musicManager.Playlist;
import com.pl.musicManager.Song;
import com.pl.musicManager.SongList;
import com.pl.musicManager.management.Library;

public class JsonHelper {

	private JsonSerializer<SongList> songlistSerializer;
	private JsonSerializer<Album> albumSerializer;
	private JsonSerializer<Artist> artistSerializer;

	
	private JsonDeserializer<SongList> songListDeserializer;
	private JsonDeserializer<Playlist> playlistDeserializer;
	private JsonDeserializer<Album> albumDeserializer;
	private JsonDeserializer<Artist> artistDeserializer;
	
	
	public JsonHelper() {
		songlistSerializer = null;
		albumSerializer = null;
		artistSerializer = null;
		
		songListDeserializer = null;
		playlistDeserializer = null;
	}
	
	public JsonSerializer<SongList> getSonglistSerializer(){
		if(songlistSerializer == null) {
			songlistSerializer = new JsonSerializer<SongList>(){
				@Override
				public JsonElement serialize(SongList src, Type type, JsonSerializationContext context) {
					
					JsonObject obj = new JsonObject();
					obj.addProperty("title", src.getTitle());
					JsonArray ids = new JsonArray();
					for(Song song : src.get()) {
						ids.add(song.getId());
					}
					obj.add("songs", ids);
					return obj;
				};
			};
		}
		return songlistSerializer;
	}
	
	public JsonSerializer<Album> getAlbumSerializer(){
		if(albumSerializer == null) {
			albumSerializer = new JsonSerializer<Album>() {
				@Override
				public JsonElement serialize(Album src, Type type, JsonSerializationContext context) {
					JsonObject result = new JsonObject();
					result.addProperty("title", src.getTitle());
					result.addProperty("id", src.getId());
					result.addProperty("artist", src.getArtist().get());
					result.addProperty("release.year", src.getReleaseYear());
					result.addProperty("length.in.seconds", src.getLengthInSeconds());
					
					JsonArray ids = new JsonArray();
					for(Song song : src.get()) {
						ids.add(song.getId());
					}
					result.add("songs", ids);
					
					return result;
				}
			};
		}
		return albumSerializer;
	}

	public JsonSerializer <Artist> getArtistSerializer(){
		if(artistSerializer == null) {
			artistSerializer = new JsonSerializer<Artist>() {
				@Override
				public JsonElement serialize(Artist src, Type type, JsonSerializationContext context) {
					JsonObject obj = new JsonObject();
					obj.addProperty("name", src.getName().get());
					JsonArray ids = new JsonArray();
					for(Album album : src.getAlbums()) {
						ids.add(album.getId());
					}
					obj.add("songs", ids);
					return obj;
				}
			};
		}
		return artistSerializer;
	}
	

	/**
	 * Custom SongList deserialier, extended with check whether the songs directory exist
	 * */
	public JsonDeserializer<SongList> getSongListDeserializer() {
		if(songListDeserializer == null) {
			
			songListDeserializer = new JsonDeserializer<SongList>(){
				@Override
					public SongList deserialize(JsonElement src, Type type, JsonDeserializationContext context)
							throws JsonParseException {
						JsonObject json = src.getAsJsonObject();
						JsonArray songs = json.getAsJsonArray("songs");
						
						int id;
						String directory;
						String title;
						String artist;
						String album;
						Duration length;
						int playCount;
						
						SongList sl = new SongList("main.songlist");
						for(JsonElement tmp : songs) {
							JsonObject song = tmp.getAsJsonObject();
							
							directory = song.get("directory").getAsString();
							File file = new File(directory);
							
							if(file.exists()) {
								title = song.get("title").getAsString();
								artist = song.get("artist").getAsString();
								album = song.get("album").getAsString();
								length = Duration.ofSeconds(song.get("lengthInSeconds").getAsInt());
								playCount = song.get("playCount").getAsInt();
								id = song.get("id").getAsInt();
								Song newSong = new Song(id, directory,title, artist, album, length, playCount);
								sl.add(newSong);
							}else {
								Logger.debug(directory + " file not found!");
							}
						}
						return sl;
					}
			};
		}
		return songListDeserializer;
	}
	
	public JsonDeserializer<Playlist> getPlaylistDeserializer() {

		if(playlistDeserializer == null) {
			playlistDeserializer = new JsonDeserializer<Playlist>(){

				@Override
				public Playlist deserialize(JsonElement src, Type type, JsonDeserializationContext context)
						throws JsonParseException {
					
					SongList songList = Library.getSongList();
					JsonObject obj = src.getAsJsonObject();
					List<Song> songs = new LinkedList<Song>();
					
					String title = obj.get("title").getAsString();
					JsonArray ids = obj.get("songs").getAsJsonArray();
					for(JsonElement id : ids) {
						Song song = songList.getSong(id.getAsInt());
						if(song != null) {
							songs.add(song);
						}
					}
					
					return new Playlist(title, songs);					
				}
				
			};
		}
		return playlistDeserializer;
	}
	
	public JsonDeserializer<Album> getAlbumDeserializer(){
		if(albumDeserializer == null){
			albumDeserializer = new JsonDeserializer<Album>() {

				@Override
				public Album deserialize(JsonElement src, Type type, JsonDeserializationContext context)
						throws JsonParseException {
					
					return null;
				}
				
			};
		}
		return albumDeserializer;
	}
}
