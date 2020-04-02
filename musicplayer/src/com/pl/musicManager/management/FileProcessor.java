package com.pl.musicManager.management;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;

import com.pl.musicManager.Album;
import com.pl.musicManager.Artist;
import com.pl.musicManager.Song;
import com.pl.utility.Explorer;
import com.pl.utility.Logger;

import javafx.scene.image.Image;

public class FileProcessor {
	
	private static List<Song> songList;
	static {
		songList = new LinkedList<Song>();
		FileProcessor.parse();
	}
	
	public FileProcessor() {
			
	}
	
	public static List<Song> getSongList(){
		return songList;
	}
	
	/**
	 * Method parses List of Files retrieved from Explorer class
	 * into List of Song objects */
	private static void parse(){
		int id = 0;
		for(File file : Explorer.getFileList()) {
			Logger.debug("Parsing file " + file.getAbsolutePath());
			AudioFile audioFile;
			
			try {
				
				audioFile = AudioFileIO.read(file);
				Tag tag = audioFile.getTag();
				AudioHeader header = audioFile.getAudioHeader();
				String directory = file.getAbsolutePath();
				String title = tag.getFirst(FieldKey.TITLE);
				String artist = tag.getFirst(FieldKey.ARTIST);
				
				String album = tag.getFirst(FieldKey.ALBUM);
				if(album.equals("")) {
					album = null;
				}
				
				long length = header.getTrackLength();
				
				Song song = new Song(id++, directory, title, artist, album, Duration.ofSeconds(length), 0);
				Logger.debug("Adding " + artist + " - " + title );
				songList.add(song);
				
			} catch (Exception e) {
				Logger.debug("Error occurred - Skipped file " + file.getAbsolutePath());
			}			
		}
	}
		
	public static int retrieveAlbumReleaseYear(File file) {
		AudioFile audioFile;
		try {
			audioFile = AudioFileIO.read(file);
			Tag tag = audioFile.getTag();
			String yearString = tag.getFirst(FieldKey.YEAR);
			return Integer.parseInt(yearString);
		}catch(Exception e) {
			return -1;
		}
	}
	
	public static Image retrieveAlbumCover(File file) {
		
		try {
			MP3File mp3 = new MP3File(file);
			Artwork artwork = mp3.getTag().getFirstArtwork();
			byte[] bytes = artwork.getBinaryData();
			return new Image(new ByteArrayInputStream(bytes));
			
			
		} catch(Exception e) {
			e.printStackTrace();
			return Album.getCoverPlaceholder();
		}
	}
	
}
