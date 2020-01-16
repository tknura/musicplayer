package com.pl.utility;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pl.musicManager.SongList;

public class CustomSonglistSerializer implements JsonSerializer<List<SongList>> {

	public CustomSonglistSerializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public JsonElement serialize(List<SongList> songList, Type arg1, JsonSerializationContext arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
