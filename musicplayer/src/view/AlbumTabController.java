package view;

import java.time.Duration;

import com.pl.musicManager.Album;
import com.pl.musicManager.Song;
import com.pl.musicManager.management.Library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AlbumTabController {
	@FXML private TableView<Album> albumTableView;
	
    @FXML private TableColumn<Album, String> nameCol;
    
    public void initialize(){
   	
    	ObservableList<Album> albumOList = FXCollections.observableList(Library.getAlbumList());
    	if(!albumOList.isEmpty()) {
    		albumTableView.setItems(albumOList);
        	
        	nameCol.setCellValueFactory(new PropertyValueFactory<Album, String>("title"));
        	
        	albumTableView.getColumns().setAll(nameCol);
    	}
    }
}
