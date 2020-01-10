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
	}
	
	public FileProcessor() {
	
		
	}
	
	private static List<Song> getSongList(){
		return songList;
	}
	
	private static void process(){
		for(File file : Explorer.getFileList()) {
			songList.add(process(file));
		}
	}
	
	
	private static Song process(File file) {
//		try {
//			AudioFile audioFile = AudioFileIO.read(file);
//			Tag tag = audioFile.getTag();
//			AudioHeader header = audioFile.getAudioHeader();
//			String title = tag.getFirst(FieldKey.TITLE);
//			String artst = tag.getFirst(FieldKey.ALBUM_ARTIST);
//			String album = tag.getFirst(FieldKey.ALBUM);
//			String length = Integer.toString(header.getTrackLength());
//			Duration duration = Duration.ofSeconds(header.getAudioDataLength());
//			int playCount = 
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
		return null;
	}
	
	
	
	
}
