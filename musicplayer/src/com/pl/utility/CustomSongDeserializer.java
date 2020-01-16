package com.pl.utility;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.pl.musicManager.Song;

public class CustomSongDeserializer implements JsonDeserializer<Song> {

	public CustomSongDeserializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Song deserialize(JsonElement songString, Type typeOfSrc, JsonDeserializationContext context) {
		try {
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
