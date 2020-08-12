package view;

import com.pl.musicManager.MusicStructure;
import com.pl.musicManager.Playlist;
import com.pl.musicManager.Song;
import com.pl.musicManager.management.Library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SongTabController extends SongTableController {
	
	@FXML private TableView<Song> songTableView;
	@FXML private TableColumn<Song, String> songCol;
    @FXML private TableColumn<Song, String> albumCol;
    @FXML private TableColumn<Song, String> aritstCol;
    
    @SuppressWarnings("unchecked")
	@Override
    public void fillTableView(MusicStructure value) {
    	ObservableList<Song> songOList = FXCollections.observableList(value.getSongs());
    	songTableView.setItems(songOList);
    	
    	songCol.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
    	albumCol.setCellValueFactory(new PropertyValueFactory<Song, String>("album"));
    	aritstCol.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
    	
    	songTableView.getColumns().setAll(songCol, albumCol, aritstCol);
    }

	@Override
	public void refresh() {
		//TODO 
	}

	@Override
	public void init() {
    	Playlist songList = new Playlist("Songs");
    	songList.add(Library.getSongList().get());
    	fillTableView(songList);
    	setDoubleClickBehaviour(songTableView);
	}
}

