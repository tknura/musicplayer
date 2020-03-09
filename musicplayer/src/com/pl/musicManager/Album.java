package com.pl.musicManager;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class Album extends SongList implements Comparable<Album> {
	
	private SimpleStringProperty artist;
	private int releaseYear;
	private long lengthInSeconds;
	private Image cover;
	private SimpleObjectProperty<Image> coverProperty;
	private int id;
	
	public Album(int id, String title, String artist, int releaseYear, Image cover) {
		super(title);
		this.id = id;
		if(title == null) {
			title = "Unknown album";
		}
		if(artist == null) {
			artist = "Unknown artist";
		}
		
		this.artist = new SimpleStringProperty(artist);
		this.lengthInSeconds = 0;
		this.releaseYear = releaseYear;
		this.cover = cover;
		this.coverProperty = new SimpleObjectProperty<Image>(cover);
		
	}

	public SimpleStringProperty getArtist() {
		return artist;
	}

	public void setArtist(SimpleStringProperty artist) {
		this.artist = artist;
	}

	public long getLengthInSeconds() {
		return lengthInSeconds;
	}

	public void setLengthInSeconds(long lengthInSeconds) {
		this.lengthInSeconds = lengthInSeconds;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Image getCover() {
		return cover;
	}

	public void setCover(Image cover) {
		this.cover = cover;
	}

	public int getId() {
		return id;
	}
	public SimpleObjectProperty<Image> getCoverProperty() {
		return coverProperty;
	}

	public void setCoverProperty(SimpleObjectProperty<Image> coverProperty) {
		this.coverProperty = coverProperty;
	}
	
	@Override
	public int compareTo(Album album) {
		return this.getTitle().toString().compareTo(album.getTitle().toString());
	}
	
	private long calculateAlbumLength() {
		long result = 0;
		
		for(Song s : songs) {
			result += s.getLengthInSeconds();
		}
		
		return result;
	}
	
	public void add(Song song) {
		songs.add(song);
		lengthInSeconds += song.getLengthInSeconds();
	}
	  

}
