package com.pl.utility.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pl.utility.Logger;


public class Config {
	
	private static Boolean verbose = false;
	private static List<String> directories;
	private static List<String> extensions;
	
 	public Config() {
 		
	}
 	
 	public static boolean isSupportedExtension(String extension) {
 		switch(extension) {
 		case".mp3":
 		case ".mp4": 
 			return true;
 		default:
 			return false;
 		}
 		
 	}

 	public static List<String> getDirectories(){
 		return Config.directories;
 	}
 	
 	public static List<String> getExtensions(){
 		return Config.extensions;
 	}
 	
 	public static Boolean getVerbose() {
 		return verbose;
 	}
 	
 	public static void setDirectories(String[] dirs) {
 		Config.directories = Arrays.asList(dirs);
		
 	}
 	
 	public static void setExtensions(String[] extensions) {
 		Config.extensions = new ArrayList<>();
		for(String extension : extensions) {
			Logger.debug("Extension: " + extension);
			if(isSupportedExtension(extension)){
				Config.extensions.add(extension);
			}
		}
 	}
 	
 	public static void printConfig() {
 		System.out.println("Directories:");
 		for(String directory : Config.directories) {
 			System.out.println(directory);
 		}
 		
 		System.out.println("Extensions:");
 		for(String extensons : Config.extensions) {
 			System.out.println(extensons);
 		}
 	}
 	
}
