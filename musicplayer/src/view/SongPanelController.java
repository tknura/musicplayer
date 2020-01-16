package view;

import java.time.Duration;

import com.pl.musicManager.Song;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SongPanelController {
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
    		Song tmp = new Song(i, "directory", "title", "artist", "album1", Duration.ofSeconds(100), i );
    		songOList.add(tmp);
		}
    	System.out.println(songOList);
		return songOList;
    }
}
