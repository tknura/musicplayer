package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.pl.musicManager.Album;
import com.pl.musicManager.Player;
import com.pl.musicManager.Song;
import com.pl.musicManager.management.Library;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class MainSceneController {
	
	@FXML private StackPane player;
	@FXML public PlayerController playerController;
	
	@FXML public void initialize() throws FileNotFoundException {	
		
	}
	
}
