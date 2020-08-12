package view;

import com.pl.musicManager.Album;
import com.pl.musicManager.MusicStructure;
import com.pl.musicManager.Playlist;
import com.pl.musicManager.Song;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class DetailedTabController extends SongTableController {

	@FXML private AnchorPane root;
	@FXML private ImageView cover;
	@FXML private Label title;
	@FXML private Label author;
	@FXML private Button backButton;
	@FXML private TableView<Song> tableView;
	@FXML private TableColumn<Song, String> songCol;
	@FXML private TableColumn<Song, String> durationCol;
	
	@Override
	public void init(){
		setDoubleClickBehaviour(tableView);
		setVisible(false);
	}

	public void load(Album album) {
		title.setText(album.getTitle());
		cover.setImage(album.getCover());
		author.setText(album.getArtist().get());
		fillTableView(album);
		setVisible(true);
	}
	
	public void load(Playlist playlist) {
		title.setText(playlist.getTitle());
		fillTableView(playlist);
		setVisible(true);
	}
	
	public void handleBackButton() {
		setVisible(false);
	}
	
	public void setVisible(boolean value) {
		root.setVisible(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void fillTableView(MusicStructure value) {
		ObservableList<Song> songsOList = FXCollections.observableList(value.getSongs());
		tableView.setItems(songsOList);
    	
		songCol.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
		durationCol.setCellValueFactory(new PropertyValueFactory<Song, String>("length"));
		
		tableView.getColumns().setAll(songCol, durationCol);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
