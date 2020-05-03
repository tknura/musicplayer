package com.pl.utility.json;

import java.lang.reflect.Type;
import java.util.Collection;

import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public abstract class JsonHandler {
	
	public <T> JsonElement  parseToJson(T obj) {
		Gson gson = FxGson.coreBuilder().setPrettyPrinting().create();
		return gson.toJsonTree(obj);
	}
	
	public <T> T parseFromJson(JsonElement src, Type type) {
		Gson gson = FxGson.coreBuilder().setPrettyPrinting().create();
		//System.out.println(gson.toJson(src).toString());
		return gson.fromJson(src, type);
	}
	
	
	public <T> JsonElement parseCollectionToJson(Collection<T> src) {
		if(src != null) {
			JsonArray parseResult = new JsonArray();
			
			for(T item : src) {
				parseResult.add(parseToJson(item));
			}
			return parseResult;
		}
		return null;
		
	}
	
	public <T> Collection<T> parseCollectionFromJson(JsonElement src, Type type) {
		Gson gson = FxGson.coreBuilder().setPrettyPrinting().create();
		if(src != null) {
			return gson.fromJson(src, type);
		}
		return null;
	}
	

}
