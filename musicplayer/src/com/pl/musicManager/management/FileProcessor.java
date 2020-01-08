package com.pl.musicManager.management;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

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
		//File -> Song obj
		return null;
	}
	
	
	
	
}
