package com.pl.utility;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import com.pl.configuration.Config;

public class Explorer {

	private static List<File> fileList;
	private static FileFilter fileFilter;
	
	static {
		//Initialize list
		fileList = new ArrayList<File>();
		
		//Create filter accepting all selected extensions
		fileFilter = new FileFilter() {
			@Override
			public boolean accept(File f) {
				if(f.isDirectory()) {
					return true;
				}
				String fileName = f.getName();
				String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
				return  Config.getExtensions().contains(fileExtension);
			}
		};
		
		//Explore directories and collect files
		exploreDirectories();
	}
	
	public Explorer() {

	}
	
	public static List<File> getFileList(){
		return fileList;
	}
	
	public static void exploreDirectories() {
		for(String directory : Config.getDirectories()) {
			addFilesToList(directory);
		}
	}
		
	private static void addFilesToList(String startDirectory){
		try {
			File dir = new File(startDirectory);
			File[] files = dir.listFiles(fileFilter);
			
			for(File file : files) {
				if(file.isDirectory()) {
					addFilesToList(file.getAbsolutePath());

				}else if(file.isFile()) {
					fileList.add(file);
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void printSongList() {
		for(File file : fileList) {
			System.out.println(file.getAbsolutePath());
		}
	}
	
	
	
}
