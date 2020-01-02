package com.pl.wylegly.utility;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Explorer {

	private List<File> songList;
	private String mainDirectoryPath;
	
	
	public Explorer(String mainDirectoryPath) {
		this.mainDirectoryPath = mainDirectoryPath;
		songList = new ArrayList<File>();

	}
	
	public void exploreDirectories(String filter) {
		
		//Filter accepts files with specified format or directories
		FileFilter myFilter = new FileFilter() {

			@Override
			public boolean accept(File f) {
				System.out.println(f.getName());
				return f.getName().endsWith(filter) || f.isDirectory();
			}
			
		};
		
		listFiles(this.mainDirectoryPath, myFilter);
	}
	
	private void listFiles(String startDirectory, FileFilter filter){
		
		try {
			File dir = new File(startDirectory);
			File[] files = dir.listFiles(filter);
			for(File file : files) {
				if(file.isDirectory()) {
					listFiles(file.getAbsolutePath(), filter);

				}else {
					//file with specifeid format found

				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public List<File> getSongList(){
		return this.songList;
	}
	
	public void printSongList() {
		for(File file : songList) {
			System.out.println(file.getAbsolutePath());
		}
	}
	
	
	
}
