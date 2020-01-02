package com.pl.wylegly.utility;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class Explorer {

	private List<File> fileList;
	private String mainDirectoryPath;
	
	
	public Explorer(String mainDirectoryPath) {
		this.mainDirectoryPath = mainDirectoryPath;
		fileList = new ArrayList<File>();

	}
	
	public void exploreDirectories(String filter) {
		
		//Filter accepts files with specified format or directories
		FileFilter myFilter = new FileFilter() {

			@Override
			public boolean accept(File f) {
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

				}else if(file.isFile()) {
					this.fileList.add(file);
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public List<File> getFileList(){
		return this.fileList;
	}
	
	public void printSongList() {
		for(File file : fileList) {
			System.out.println(file.getAbsolutePath());
		}
	}
	
	
	
}
