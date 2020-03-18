package com.pl.utility.json;

import java.lang.reflect.Type;
import java.util.Collection;

import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.pl.musicManager.SongList;

public class SongListJsonHandler extends JsonHandler {

	@Override
	public <T> T parseFromJson(JsonElement src) {
		GsonBuilder customGsonBuilder = FxGson.coreBuilder(); 
		Gson gson = customGsonBuilder.setPrettyPrinting().create();
		return (T) gson.fromJson(src, SongList.class);

	}

	@Override
	public <T> Collection<T> parseCollectionFromJson(JsonArray src, Type type) {
		// TODO Auto-generated method stub
		return null;
	}

}
