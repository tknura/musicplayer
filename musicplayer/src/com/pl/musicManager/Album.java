package com.pl.musicManager;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class Album extends SongList implements Comparable<Album> {
	
	private SimpleStringProperty title;
	private SimpleStringProperty artist;
	private int releaseYear;
	private long lengthInSeconds;
	private Image cover;
	private SimpleObjectProperty<Image> coverProperty;

	
	public Album(String title, String artist, int releaseYear, Image cover) {
		
		if(title == null) {
			title = "Unknown album";
		}
		if(artist == null) {
			artist = "Unknown artist";
		}
		
		this.title = new SimpleStringProperty(title);
		this.artist = new SimpleStringProperty(artist);
		this.lengthInSeconds = CalculateAlbumLength();
		this.releaseYear = releaseYear;
		this.cover = cover;
		this.coverProperty = new SimpleObjectProperty<Image>(cover);
		
	}
	
	public SimpleStringProperty getTitle() {
		return title;
	}

	public void setTitle(SimpleStringProperty title) {
		this.title = title;
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

	public SimpleObjectProperty<Image> getCoverProperty() {
		return coverProperty;
	}

	public void setCoverProperty(SimpleObjectProperty<Image> coverProperty) {
		this.coverProperty = coverProperty;
	}
	
	@Override
	public int compareTo(Album album) {
		return this.title.toString().compareTo(album.getTitle().toString());
	}
	
	private long CalculateAlbumLength() {
		long result = 0;
		
		for(Song s : songs) {
			result += s.getLengthInSeconds();
		}
		
		return result;
	}

}
