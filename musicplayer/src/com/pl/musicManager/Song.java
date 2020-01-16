package com.pl.musicManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.Duration;
import java.util.UUID;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Song implements Comparable<Song>{
	
	
	private SimpleStringProperty title;
	private SimpleStringProperty artist;
	private SimpleStringProperty album;
	private SimpleStringProperty length;
	private long lengthInSeconds;
	private SimpleIntegerProperty playCount;
	private String directory;
	private SimpleBooleanProperty selected;
	private SimpleBooleanProperty playing;
	private int id;
	
	public Song(int id, String directory, String title, String artist, String album, Duration length, int playCount) {
		this.id = id;
		this.directory = directory;
		
		if(title == null) {
			Path path = Paths.get(directory);
			String temp = path.getFileName().toString();
			System.out.println("temp: " + temp);
			//temp.substring(0, temp.lastIndexOf("."));
		}
		if(artist == null) {
			artist = "Unknown artist";
		}
		if(album == null) {
			album = "Unknown album";
		}
		
		this.title = new SimpleStringProperty(title);
		this.artist = new SimpleStringProperty(artist);
		this.album = new SimpleStringProperty(album);
		
		long seconds = length.getSeconds();
		this.length = new SimpleStringProperty(length.toMinutes() + ":" +
								(seconds < 10 ? "0" + seconds : seconds));
		
		this.lengthInSeconds = length.getSeconds();
		
		this.playCount = new SimpleIntegerProperty(playCount);
		this.selected = new SimpleBooleanProperty(false);
		this.playing = new SimpleBooleanProperty(false);
		
	}

	public int getId() {
		return this.id;
	}
	
	public String getDirectory() {
		return this.directory;
	}

	public String getTitle() {
		return title.get();
	}


	public String getArtist() {
		return artist.get();
	}
	
	public String getAlbum() {
		return album.get();
	}
	
	public String getLength() {
		return length.get();
	}
	
	public long getLengthInSeconds(){
		return lengthInSeconds;
	}
	
	public int getPlayCount() {
		return playCount.get();
	}
	
	public StringProperty getTitleProperty(){
		return title;
	}
	
	public StringProperty getArtistProperty() {
		return artist;
	}
	
	public StringProperty getAlbumProperty() {
		return album;
	}
	
	public StringProperty getLengthProperty() {
		return length;
	}	
	
	public void setPlayCount(int playCount) {
		this.playCount = new SimpleIntegerProperty(playCount);
	}
	
	public void played() {
		setPlayCount(this.playCount.get() + 1);
		//TO DO:
		//adding to lastPlayedList in library 	
	}
	
	public void setSelected(boolean selected) {
		this.selected = new SimpleBooleanProperty(selected);
	}
	
	public void setPlaying(boolean playing) {
		this.playing = new SimpleBooleanProperty(playing);
	}

	@Override
	public int compareTo(Song song) {
		return this.getTitle().compareTo(song.getTitle());
	}

	public void print() {
		System.out.println("ID: " + id);
		System.out.println("Directory: " + directory);
		System.out.println("Title: " + title);
		System.out.println("Artist: " + artist);
		System.out.println("Album: " + album);
		System.out.println("Length: " + length);
		System.out.println("Length in seconds: " + lengthInSeconds);
		System.out.println("Play count: " + playCount);

	}
}
