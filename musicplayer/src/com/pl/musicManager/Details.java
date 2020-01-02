package com.pl.musicManager;

import java.awt.image.BufferedImage;
import java.time.Duration;

public class Details {
	
	private Duration duration;
	private String name;
	private String artist;
	private BufferedImage imageHandler;
	
	public Details(Duration duration, String name, String artist, BufferedImage imageHandler) {
		super();
		this.duration = duration;
		this.name = name;
		this.artist = artist;
		this.imageHandler = imageHandler;
	}

	private void retrieveImage() {
		
	}

}
