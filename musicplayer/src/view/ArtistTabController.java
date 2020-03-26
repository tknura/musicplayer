package view;

import java.time.Duration;

import com.pl.musicManager.Artist;
import com.pl.musicManager.Song;
import com.pl.musicManager.management.Library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ArtistTabController {
	@FXML private TableView<Artist> artistTableView;
	
	@FXML private TableColumn<Artist, String> nameCol;
	
    
    public void initialize(){
   	
    	ObservableList<Artist> artistOList = FXCollections.observableList(Library.getArtistCollections());
    	artistTableView.setItems(artistOList);
    	
    	nameCol.setCellValueFactory(new PropertyValueFactory<Artist, String>("name"));
    	
    	artistTableView.getColumns().setAll(nameCol);
    }
}
