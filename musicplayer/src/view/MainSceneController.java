package view;

import javafx.fxml.FXML;
public class MainSceneController {
	
	@FXML public PlayerController playerController;
	@FXML public SongTabController songTabController;
	@FXML public AlbumTabController albumTabController;
	@FXML public ArtistTabController artistTabController;
	@FXML public PlaylistsTabController playlistsTabController;
	@FXML public SongDisplayController songDisplayController;
	
	@FXML public void initialize() {	
		songTabController.injectMainController(this);
		playerController.injectMainController(this);
		albumTabController.injectMainController(this);
		artistTabController.injectMainController(this);
		playlistsTabController.injectMainController(this);
	}
}
