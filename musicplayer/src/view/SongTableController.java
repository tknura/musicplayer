package view;

import com.pl.musicManager.MusicStructure;
import com.pl.musicManager.Playlist;
import com.pl.musicManager.Song;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

public abstract class SongTableController extends TabController<Song> {
	
	public abstract void fillTableView(MusicStructure value);
	
	public void setDoubleClickBehaviour(TableView<Song> tableView) {
		tableView.setRowFactory( tempTableView->{
    		final TableRow<Song> row = new TableRow<>();
    		row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Playlist temp = new Playlist("");
                    temp.add(tableView.getItems());
                    msc.playerController.loadAndPlay(row.getItem(), temp);
                }
    		});
    		return row;
    	});
	}
	
	public void setRightClickBehaviour() {
	}
}
