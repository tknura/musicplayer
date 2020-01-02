package com.pl.musicManager;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.pl.musicManager.management.FileProcessor;

public class MainSongList{
	private SongList songList;
	public MainSongList() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		FileProcessor fp = new FileProcessor(".mp3");
		List<File> files = fp.getFileList();
		for(File file : files) {
			//songList.add(new Song(file));
		}
	}

}
