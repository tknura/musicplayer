package com.pl.musicManager;

import java.util.Comparator;
import java.util.LinkedList;

import javafx.beans.property.SimpleStringProperty;

public class Artist implements Comparable<Artist>{
	
	private SimpleStringProperty name;
	private LinkedList<Album> albums;
	
	public Artist(String name) {
		super();
		this.name = new SimpleStringProperty(name);
		this.albums = new LinkedList<Album>();
	}

	public SimpleStringProperty getName() {
		return name;
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public LinkedList<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(LinkedList<Album> albums) {
		this.albums = albums;
	}
	
	public void addAlbum(Album album) {
		albums.add(album);
	}
	
	public void removeAlbum(Album album) {
		albums.remove(album);
	}
	
	public void sortByReleaseDate() {
		Comparator<Album> compareByDate = (Album a1, Album a2) -> Long.compare(a1.getLengthInSeconds(), a2.getReleaseYear());
		albums.sort(compareByDate);
	}
	
	@Override
	public int compareTo(Artist artist) {
		return this.name.toString().compareTo(artist.getName().toString());
	}
}
