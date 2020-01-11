package view;

import java.time.Duration;
import java.util.LinkedList;

import com.pl.musicManager.Album;
import com.pl.musicManager.Artist;
import com.pl.musicManager.Song;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import java.util.LinkedList;

public class MainSceneController {
	
	@FXML private Label songnameLabel;
	
	@FXML private Label artistnameLabel;
	
	@FXML private ImageView coverDisplay;

	@FXML private Group pauseIcon;

	@FXML private Group playIcon;

	@FXML private Button nextButton;

	@FXML private Button playPauseButton;
	
	@FXML private Button prevButton;
	
	@FXML private ToggleButton queueButton;

	@FXML private ToggleButton shuffleButton;

	@FXML private ToggleButton volumeButton;

	@FXML private ToggleButton repeatButton;
	
	@FXML private TableView<Song> songTableView;
	
	@FXML private TableColumn<Song, String> songCol;
	
    @FXML private TableColumn<Song, String> albumCol;
    
    @FXML private TableColumn<Song, String> aritstCol;
    
    public void initialize(){
    	ObservableList<Song> songOList = generatetest();
    	songTableView.setItems(songOList);
    	
    	songCol.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
    	albumCol.setCellValueFactory(new PropertyValueFactory<Song, String>("album"));
    	aritstCol.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
    	
    	songTableView.getColumns().setAll(songCol, albumCol, aritstCol);
    }

    private ObservableList<Song> generatetest() {
    	ObservableList<Song> songOList = FXCollections.observableArrayList();
    	for (int i = 0; i < 10; i++) {
    		Song tmp = new Song("test" + Integer.toString(i), "test" + Integer.toString(i), 
    				"test" + Integer.toString(i), Duration.ofSeconds(100), 1, "test", i);
    		songOList.add(tmp);
		}
    	System.out.println(songOList);
		return songOList;
    }
}
