package view;

import com.pl.musicManager.Playlist;
import com.pl.musicManager.management.Library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PlaylistsTabController extends TabController<Playlist> {
	
	private MainSceneController msc;
	
	@FXML private TableView<Playlist> albumTableView;
    @FXML private TableColumn<Playlist, String> nameCol;
    @FXML private DetailedTabController detailedTabController;
    @FXML private Button addPlaylistButton;
    @FXML private NewPlaylistController newPlaylistController;
    
    @Override
    public void init(){
    	fillTable();
    	setDoubleClickBehaviour(albumTableView);
    	detailedTabController.injectMainController(msc);
    	newPlaylistController.injectParent(this);
    }

	@Override
	public void refresh() {
		fillTable();
	}
	
	@SuppressWarnings("unchecked")
	public void fillTable() {
		ObservableList<Playlist> playlistOList = FXCollections.observableList(Library.getPlaylistList());
    	if(!playlistOList.isEmpty()) {
    		albumTableView.setItems(playlistOList);
        	
        	nameCol.setCellValueFactory(new PropertyValueFactory<Playlist, String>("title"));
        	
        	albumTableView.getColumns().setAll(nameCol);
    	}
	}
	
	@Override
	public void setDoubleClickBehaviour(TableView<Playlist> tableView) {
    	tableView.setRowFactory( tempTableView->{
    		final TableRow<Playlist> row = new TableRow<>();
    		row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                	detailedTabController.load(row.getItem());
                }
    		});
    		return row;
    	});
	}
	
	public void handleAddPlaylistButton() {
		newPlaylistController.setVisible(true);
	}
	
}
