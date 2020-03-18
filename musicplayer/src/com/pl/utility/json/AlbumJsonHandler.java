package com.pl.utility.json;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pl.musicManager.Album;
import com.pl.musicManager.MusicStructure;
import com.pl.utility.Logger;

public class AlbumJsonHandler extends JsonHandler {

	@Override
	public <T> T parseFromJson(JsonElement src) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T> Collection<T> parseCollectionFromJson(JsonArray src, Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
