package com.pl.musicManager.management;

import java.io.File;
import java.util.List;

import com.pl.musicManager.SongList;
import com.pl.utility.Explorer;

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
