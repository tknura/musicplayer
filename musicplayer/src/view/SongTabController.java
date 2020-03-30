package view;

import java.time.Duration;

import com.pl.musicManager.Player;
import com.pl.musicManager.Song;
import com.pl.musicManager.management.Library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SongTabController {
	
	private MainSceneController mainSceneController;
	
	@FXML private TableView<Song> songTableView;
	
	@FXML private TableColumn<Song, String> songCol;
	
    @FXML private TableColumn<Song, String> albumCol;
    
    @FXML private TableColumn<Song, String> aritstCol;
    
    public void initialize(){
    	ObservableList<Song> songOList = FXCollections.observableList(Library.getSongList().get());
    	songTableView.setItems(songOList);
    	
    	songTableView.setRowFactory( tableView->{
    		final TableRow<Song> row = new TableRow<>();
    		row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                   mainSceneController.playerController.loadAndPlay(row.getItem());
                }
    		});
    		return row;
    	});
    	
    	songCol.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
    	albumCol.setCellValueFactory(new PropertyValueFactory<Song, String>("album"));
    	aritstCol.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
    	
    	songTableView.getColumns().setAll(songCol, albumCol, aritstCol);
    }
    
    public void injectMainController(MainSceneController mainSceneController) {
    	this.mainSceneController = mainSceneController;
    }
    
}
