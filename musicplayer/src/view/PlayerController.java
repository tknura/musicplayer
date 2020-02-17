package view;

import java.time.Duration;

import com.pl.musicManager.Player;
import com.pl.musicManager.Song;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;

public class PlayerController {
	
	@FXML private Group pauseIcon;

	@FXML private Group playIcon;

	@FXML private Button nextButton;

	@FXML private ToggleButton playPauseButton;
	
	@FXML private Button prevButton;
	
	@FXML private ToggleButton queueButton;

	@FXML private ToggleButton shuffleButton;

	@FXML private ToggleButton volumeButton;

	@FXML private ToggleButton repeatButton;
	
	@FXML private Label songDuration;
	
	@FXML private Slider timeSlider;
	
	@FXML private ProgressBar timeProgressBar;
	
	@FXML public void initialize() {
		Song song = new Song(1, "E:/Repositories/musicplayer/test.mp3", "schafter", "schafter", "schafter", Duration.ofSeconds(1) , 0);
		Player.setCurrentPlayingSong(song);
		Player.Load(song);
		
		timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
            	timeProgressBar.setProgress(new_val.doubleValue()/100);
            }
        });
//	    Player.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
//	    	timeSlider.setValue(newValue.toSeconds());
//	    });
	}
	
	@FXML private void handleNextButton(ActionEvent event) {
		
	}
	
	@FXML private void handlePrevButton(ActionEvent event) {
		
	}
	
	@FXML private void handlePlayPauseButton(ActionEvent event) {
		if(Player.getCurrentPlayingSong() != null) {
			if(!playPauseButton.isSelected()) {
				handlePause();
			}
			else {
				handlePlay();
			}
		}
	}
	
	private void handlePlay() {
		Player.Resume();
		playIcon.setOpacity(0);
		pauseIcon.setOpacity(1);
	}
	
	private void handlePause() {
		Player.Pause();
		playIcon.setOpacity(1);
		pauseIcon.setOpacity(0);
	}

}
