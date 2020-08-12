package view;

import com.pl.musicManager.Artist;
import com.pl.musicManager.management.Library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ArtistTabController extends TabController<Artist>{
	
	@FXML private TableView<Artist> artistTableView;
	@FXML private TableColumn<Artist, String> nameCol;
    
	@SuppressWarnings("unchecked")
    public void init(){
    	ObservableList<Artist> artistOList = FXCollections.observableList(Library.getArtistCollections());
    	artistTableView.setItems(artistOList);
    	
    	nameCol.setCellValueFactory(new PropertyValueFactory<Artist, String>("name"));
    	
    	artistTableView.getColumns().setAll(nameCol);
    }

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDoubleClickBehaviour(TableView<Artist> tableView) {
		// TODO Auto-generated method stub
		
	}
    
}
