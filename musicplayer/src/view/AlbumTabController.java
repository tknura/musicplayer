package view;

import com.pl.musicManager.Album;
import com.pl.musicManager.management.Library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AlbumTabController extends TabController<Album> {
	
	@FXML private TableView<Album> albumTableView;
    @FXML private TableColumn<Album, String> nameCol;
    @FXML private DetailedTabController detailedTabController;
    
    @Override
    public void init(){
    	fillTable();
    	setDoubleClickBehaviour(albumTableView);
    	detailedTabController.injectMainController(msc);
    }

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("unchecked")
	public void fillTable() {
		ObservableList<Album> albumOList = FXCollections.observableList(Library.getAlbumList());
    	if(!albumOList.isEmpty()) {
    		albumTableView.setItems(albumOList);
        	
        	nameCol.setCellValueFactory(new PropertyValueFactory<Album, String>("title"));
        	
        	albumTableView.getColumns().setAll(nameCol);
    	}
	}
	
	@Override
	public void setDoubleClickBehaviour(TableView<Album> tableView) {
    	tableView.setRowFactory( tempTableView->{
    		final TableRow<Album> row = new TableRow<>();
    		row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                	detailedTabController.load(row.getItem());
                }
    		});
    		return row;
    	});
	}
}
