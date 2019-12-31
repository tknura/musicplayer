package com.pl.wylegly.utility;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Explorer {

	private List<Files> songList;
	private String mainDirectoryPath;
	

	public Explorer(String mainDirectoryPath) {
		this.mainDirectoryPath = mainDirectoryPath;
		songList = 
	}
	
	private void exploreDirectories(String startDirectory, String filter) {
		
		//Filter accepts specified format files or directories
		FilenameFilter myFilter = new FilenameFilter() {
			@Override
			public boolean accept(File f, String name) {
				return name.endsWith(filter) || f.isDirectory();
			}
		};
		
		listFiles(startDirectory, myFilter);
	}
	
	private void listFiles(String startDirectory, FilenameFilter filter){
		
		File dir = new File(startDirectory);
		File[] files = dir.listFiles(filter);
		
		if(files != null && files.length > 0) {
			for(File file : files) {
				if(file.isDirectory()) {
					//another directory found
					listFiles(file.getAbsolutePath(), filter);
				}else {
					//file with specifeid format found
					s
				}
			}
		}
	}
	
	private String retrieveName(File f) {
		if(f != null) {
			StringBuilder builder = new StringBuilder(f.getName());
			String temp = builder.reverse().toString();
		}
		return null;
	}
	
	
	
}
