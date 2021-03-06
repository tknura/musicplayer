package view;

import com.pl.musicManager.Song;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SongDisplayController {

	@FXML private Label songnameLabel;
	@FXML private Label artistnameLabel;
	@FXML private ImageView coverDisplay;
	
	public void loadSong(Song song) {
		songnameLabel.setText(song.getTitle().toString());
		artistnameLabel.setText(song.getArtist().toString());
		coverDisplay.setImage(song.getCover());
	}
}
