package com.pl.wylegly.utility;

import java.io.File;
import java.util.List;

import com.pl.wylegly.musicManager.SongList;

public class FileProcessor {

	private Explorer explorer;
	
	public FileProcessor(String extension) {
		this.explorer = new Explorer("C:/Users/Błażej/Desktop/test");
		explorer.exploreDirectories(extension);
	}
	
	public List<File> getFiles(){
		return explorer.getFileList();
	}
	
	
}
