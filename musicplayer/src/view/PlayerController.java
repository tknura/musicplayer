package view;

//import java.time.Duration;

import com.pl.musicManager.Player;
import com.pl.musicManager.Queue;
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
import javafx.util.Duration;

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
	@FXML private Label actualSongDuration;
	private Duration totalTime;
	
	@FXML private Slider timeSlider;
	@FXML private ProgressBar timeProgressBar;
	
	@FXML public void initialize() {
		Song song = new Song(1, "E:/Repositories/musicplayer/test.mp3", "schafter", "schafter", "schafter",  java.time.Duration.ofSeconds(182) , 0);
		loadSong(song);
		
		timeProgressBar.progressProperty().bind(timeSlider.valueProperty().divide(100));
		
		timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
            	if(timeSlider.isValueChanging()) {
            		if(Player.getMediaPlayer() != null) {
            			Player.seek(totalTime.multiply(new_val.doubleValue() / 100.0));
            		}
            		else {
            			timeSlider.valueProperty().setValue(0);
            		}
            	}
            }
        });  
	}
	
	@FXML private void handleNextButton(ActionEvent event) {
		if(Player.getUserQueue().getSong() != null) {
			loadAndPlay(Player.getSongsQueue().getSong());
		}
		else {
			loadAndPlay(Player.getUserQueue().getSong());
		}
	}
	
	@FXML private void handlePrevButton(ActionEvent event) {
		if(Player.getLastlyPlayedSongs().getSong() != Player.getCurrentPlayingSong()) {
			loadAndPlay(Player.getLastlyPlayedSongs().getSong());
		}
		else {
			loadAndPlay(Player.getCurrentPlayingSong());
		}
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
	
	@FXML private void handleQueueButton(ActionEvent event) {
		if(!queueButton.isSelected()) {
			//hide queue
		}
		else {
			//display queue
		}
	}
	
	@FXML private void handleShuffleButton(ActionEvent event) {
		if(!shuffleButton.isSelected()) {
			Player.shuffle(false);
		}
		else {
			Player.shuffle(true);
		}
	}
	
	@FXML private void handleVolumeButton(ActionEvent event) {
		if(!volumeButton.isSelected()) {			
			Player.unMute();
		}
		else {
			Player.mute();
		}
	}
	
	@FXML private void handleRepeatButton(ActionEvent event) {
		if(!repeatButton.isSelected()) {
			Player.repeat(false);
		}
		else {
			Player.repeat(true);
		}
	}
	
	public void loadSong(Song song) {
		Player.load(song);
		//SongDisplayController.instance.LoadSong(song);
		totalTime = Duration.seconds(song.getLengthInSeconds());
		songDuration.setText(numberToStringDuration((long)totalTime.toSeconds()));
		
	    Player.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
	    	actualSongDuration.setText(numberToStringDuration((long)newValue.toSeconds()));
	    	if (!timeSlider.isDisabled() && totalTime.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
	    		timeSlider.setValue(newValue.divide(totalTime.toMillis()).toMillis() * 100.0);
	    	}
	    });
	}
	
	public void loadAndPlay(Song song) {
		loadSong(song);
		handlePlay();
	}
	
	private void handlePlay() {
		if(!playPauseButton.isSelected()) {
			playPauseButton.setSelected(true);
		}
		Player.resume();
		playIcon.setOpacity(0);
		pauseIcon.setOpacity(1);
	}
	
	private void handlePause() {
		Player.pause();
		playIcon.setOpacity(1);
		pauseIcon.setOpacity(0);
	}
	
	private static String numberToStringDuration(long number) {
		return String.valueOf(number/60) + ":" + ((number%60 >= 10) ? String.valueOf(number%60) : "0" + String.valueOf(number%60) );
	}
}
