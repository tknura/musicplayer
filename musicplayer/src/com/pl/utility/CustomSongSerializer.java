package com.pl.utility;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pl.musicManager.Song;

public class CustomSongSerializer implements JsonSerializer<Song> {
	
	public CustomSongSerializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public JsonElement serialize(Song song, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(song.getId());
	}

}
