package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.pl.musicManager.Album;

//import java.time.Duration;

import com.pl.musicManager.Player;
import com.pl.musicManager.Queue;
import com.pl.musicManager.Song;
import com.pl.musicManager.management.Library;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class PlayerController {
	
	@FXML private StackPane songDisplay;
	@FXML private SongDisplayController songDisplayController;
	
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
	
	/*
	 * Initialize method which is executed after fxml loads
	 */
	@FXML public void initialize() throws IOException {	
		
		Album testAlbum = new Album("Cafe Belga", "Taco Hemingway", 2018, new Image(new FileInputStream("E:/Repositories/musicplayer/musicplayer/src/resources/placeholders/albumPlaceholder.jpg")));
		testAlbum.add(Library.getSongList().get());
		
		Player.setMainQueue(testAlbum);
		loadSong(testAlbum.front());

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
	
	/*
	 * Next button click behavior
	 */
	@FXML private void handleNextButton(ActionEvent event) {
		handleNext();
	}
	
	/*
	 * Prev button click behavior
	 */
	@FXML private void handlePrevButton(ActionEvent event) {
		loadAndPlay(Player.prev());
	}
	
	/*
	 * Pause/Play button click behavior
	 */
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
	
	/*
	 * Queue button click behavior
	 */
	@FXML private void handleQueueButton(ActionEvent event) {
		if(!queueButton.isSelected()) {
			//hide queue
		}
		else {
			//display queue
		}
	}
	
	/*
	 * Shuffle button click behavior
	 */
	@FXML private void handleShuffleButton(ActionEvent event) {
		if(!shuffleButton.isSelected()) {
			Player.shuffle(false);
		}
		else {
			Player.shuffle(true);
		}
	}
	
	/*
	 * Volume button click behavior
	 * TODO change mute button to slider controlling volume
	 */
	@FXML private void handleVolumeButton(ActionEvent event) {
		if(!volumeButton.isSelected()) {			
			Player.unmute();
		}
		else {
			Player.mute();
		}
	}
	
	/*
	 * Repeat button click behavior
	 */
	@FXML private void handleRepeatButton(ActionEvent event) {
		if(!repeatButton.isSelected()) {
			Player.repeat(false);
		}
		else {
			Player.repeat(true);
		}
	}
	
	/*
	 * Method for loading song to Player class and necessary ui behavior
	 */
	public void loadSong(Song song) {
		Player.load(song);
		songDisplayController.loadSong(song);
		totalTime = Duration.seconds(song.getLengthInSeconds());
		songDuration.setText(numberToStringDuration((long)totalTime.toSeconds()));
		
	    Player.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
	    	actualSongDuration.setText(numberToStringDuration((long)newValue.toSeconds()));
	    	if (!timeSlider.isDisabled() && totalTime.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
	    		timeSlider.setValue(newValue.divide(totalTime.toMillis()).toMillis() * 100.0);
	    	}
	    });
	    
		Player.getMediaPlayer().setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				Player.getCurrentPlayingSong().played();
				handleNext();
			}
		});
	}
	
	/*
	 * Method for loading song and playing it
	 */
	public void loadAndPlay(Song song) {
		loadSong(song);
		handlePlay();
	}
	
	/*
	 * Method for handling next song
	 */
	private void handleNext() {
		if(Player.next() != null) {
			loadAndPlay(Player.next());
		}
		else {
			loadSong(Player.getMainQueue().front());
			handlePause();
		}
	}
	
	/*
	 * Method for handling play button
	 */
	private void handlePlay() {
		if(!playPauseButton.isSelected()) {
			playPauseButton.setSelected(true);
		}
		Player.resume();
		playIcon.setOpacity(0);
		pauseIcon.setOpacity(1);
	}
	
	/*
	 * Method for handling pause button
	 */
	private void handlePause() {
		Player.pause();
		playIcon.setOpacity(1);
		pauseIcon.setOpacity(0);
	}
	
	/*
	 * Method which changes duration as long number to String which is shown in ui
	 */
	private static String numberToStringDuration(long number) {
		return String.valueOf(number/60) + ":" + ((number%60 >= 10) ? String.valueOf(number%60) : "0" + String.valueOf(number%60) );
	}
}
