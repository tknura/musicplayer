package com.pl.musicManager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;
import java.util.List;

import com.pl.musicManager.management.FileProcessor;

public class Album extends SongList implements Comparable<Album> {
	
	private Details albumDetails;

	public Album(Duration duration, String name, String artist, BufferedImage imageHandler) {
		albumDetails = new Details(duration, name , artist, imageHandler);
	}
	
	public Album(Details albumDetails) {
		super();
		this.albumDetails = albumDetails;
	}
	
	public Details getAlbumDetails() {
		return albumDetails;
	}

	public void setAlbumDetails(Details albumDetails) {
		this.albumDetails = albumDetails;
	}


	@Override
	public int compareTo(Album album) {
		return this.albumDetails.compareTo(album.getAlbumDetails());
	}
}
