package view;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

public class PlayerController {
	@FXML private Label songnameLabel;
	
	@FXML private Label artistnameLabel;
	
	@FXML private Group pauseIcon;

	@FXML private Group playIcon;

	@FXML private Button nextButton;

	@FXML private Button playPauseButton;
	
	@FXML private Button prevButton;
	
	@FXML private ToggleButton queueButton;

	@FXML private ToggleButton shuffleButton;

	@FXML private ToggleButton volumeButton;

	@FXML private ToggleButton repeatButton;
}
