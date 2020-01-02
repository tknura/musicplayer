package com.pl.musicManager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;
import java.util.List;

import com.pl.musicManager.management.FileProcessor;

public class Album extends SongList {
	
	private Details albumDetails;

	public Album(Duration duration, String name, String artist, BufferedImage imageHandler) {
		albumDetails = new Details(duration, name , artist, imageHandler);
	}
	
	public Album(Details albumDetails) {
		super();
		this.albumDetails = albumDetails;
	}
}
