package com.pl.musicManager.management;

import java.io.File;
import java.util.List;

import com.pl.utility.Explorer;

public class FileProcessor {

	private Explorer explorer;
	
	public FileProcessor(String directory, String extension) {
		this.explorer = new Explorer(directory);
		explorer.exploreDirectory(extension);
	}
	
	public List<File> getFileList() {
		return explorer.getFileList();
	}
	
	
}
