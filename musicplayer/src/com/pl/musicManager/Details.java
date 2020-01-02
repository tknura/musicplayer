package com.pl.musicManager;

import java.awt.image.BufferedImage;
import java.time.Duration;

public class Details implements Comparable<Details>{
	
	private Duration duration;
	private String name;
	private String artist;
	private BufferedImage imageHandler;
	
	private void retrieveImage() {
		//TODO retrieving images
	}
	
	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public BufferedImage getImageHandler() {
		return imageHandler;
	}

	public void setImageHandler(BufferedImage imageHandler) {
		this.imageHandler = imageHandler;
	}
	
	public Details(Duration duration, String name, String artist, BufferedImage imageHandler) {
		super();
		this.duration = duration;
		this.name = name;
		this.artist = artist;
		this.imageHandler = imageHandler;
	}
	
	@Override
	public int compareTo(Details details) {
		return this.name.compareTo(details.getName());
	}
}
