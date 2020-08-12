package view;

import com.pl.musicManager.Playlist;
import com.pl.musicManager.management.Library;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class NewPlaylistController {
	
	@FXML private PlaylistsTabController parent;
	@FXML private AnchorPane root;
	@FXML private TextField playlistNameInput;
	
	public void injectParent(PlaylistsTabController ptc) {
		setVisible(false);
		this.parent = ptc;
	}
	
	public void handleCancelButton() {
		setVisible(false);
	}
	
	public void handleCreateButton() {
		Library.addPlaylist(new Playlist(playlistNameInput.getText()));
		parent.refresh();
		playlistNameInput.clear();
		setVisible(false);
	}
	
	public void setVisible(boolean value) {
		root.setVisible(value);
	}
}
