package com.pl.utility.json;

import java.lang.reflect.Type;
import java.util.Collection;

import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.pl.utility.Logger;

public abstract class JsonHandler {
	
	public <T> JsonElement  parseToJson(T obj) {
		Gson gson = FxGson.coreBuilder().setPrettyPrinting().create();
		return gson.toJsonTree(obj);
	}
	
	public abstract <T> T parseFromJson(JsonElement src);
	
	public <T> JsonElement parseCollectionToJson(Collection<T> src) {
		if(src != null) {
			Logger.debug("Parsing collection");
			JsonArray parseResult = new JsonArray();
			
			for(T item : src) {
				parseResult.add(parseToJson(item));
			}
			return parseResult;
		}
		return null;
		
	}
	
	public abstract <T> Collection<T> parseCollectionFromJson(JsonArray src, Type type);
	

}
