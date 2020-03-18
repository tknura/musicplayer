package com.pl.utility.json; 

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.pl.musicManager.MusicStructure;
import com.pl.musicManager.Playlist;
import com.pl.utility.Logger;

public class PlaylistJsonHandler extends JsonHandler {
	
	@Override
	public Playlist parseFromJson(JsonElement src) {
		return null;
	}

	@Override
	public <T> Collection<T> parseCollectionFromJson(JsonArray src, Type type) {
		// TODO Auto-generated method stub
		return null;
	}
}
