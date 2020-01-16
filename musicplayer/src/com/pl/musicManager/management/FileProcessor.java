package com.pl.musicManager.management;

import java.io.File;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import com.pl.musicManager.Song;
import com.pl.utility.Explorer;

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
			System.out.println("Parsing file");
			AudioFile audioFile;
			try {
				//Tutaj sa errory! 
				audioFile = AudioFileIO.read(file);
				Tag tag = audioFile.getTag();
				AudioHeader header = audioFile.getAudioHeader();
				
				String directory = file.getAbsolutePath();
				String title = tag.getFirst(FieldKey.TITLE);
				String artist = tag.getFirst(FieldKey.ALBUM_ARTIST);
				String album = tag.getFirst(FieldKey.ALBUM);
				long length = header.getTrackLength();
				Duration duration = Duration.ofSeconds(header.getAudioDataLength());
				
				Song song = new Song(id++, directory, title, artist, album, Duration.ofSeconds(length), 0);
				songList.add(song);
				
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
	}
		
	
}
