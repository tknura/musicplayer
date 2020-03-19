package application;
	


import com.pl.configuration.Config;
import com.pl.musicManager.Album;
import com.pl.musicManager.Playlist;
import com.pl.musicManager.Song;
import com.pl.musicManager.management.Library;
import com.pl.utility.FontLoader;
import com.pl.utility.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {        
		FontLoader.LoadAll();
		try {
        // Read file fxml and draw interface.
        Parent root = FXMLLoader.load(getClass()
                .getResource("/view/MainScene.fxml"));
        //System.out.println(Font.getFontNames());
        SetUpScene(primaryStage, root);
     
    } catch(Exception e) {
        e.printStackTrace();
    }
} 
	
	
	public static void main(String[] args) {
		
		String[] directories = {"C:\\Java Projects\\musicplayer\\Taco Hemingway - Cafe Belga [mp3]"};
		String[] extensions = {".mp3"};
		Config config = new Config(directories, extensions);	
		Library.initializeLibrary();
//		Library.getSongList().print();
//		
//		
//		Song song1 = Library.getSongList().getSongWithIndex(0);
//		Song song2 = Library.getSongList().getSongWithIndex(1);
//		Song song3 = Library.getSongList().getSongWithIndex(2);
//		Playlist pl1 = new Playlist("temp playlist");
//		pl1.add(song1);
//		pl1.add(song2);
//		pl1.add(song3);
//		
//		Playlist pl2 = new Playlist("temp playlist twoooo");
//		pl2.add(song1);
//		pl2.add(song2);
//		pl2.add(song3);
//		
//		Playlist pl3 = new Playlist("temp playlist 3");
//		pl3.add(song1);
//		pl3.add(song2);
//		pl3.add(song3);
//		
//		Album al1 = new Album("Hollywood is bleeding", "Post Malone", 2019, null);
//		al1.add(song1);
//		al1.add(song2);
//		al1.add(song3);
//		
//		Album al2 = new Album("Schodki", "Mata", 2020, null);
//		al2.add(song1);
//		al2.add(song2);
//		al2.add(song3);
//		
//		Library.addPlaylist(pl1);
//		Library.addPlaylist(pl2);
//		Library.addPlaylist(pl3);
////		
//		Library.addAlbum(al1);
//		Library.addAlbum(al2);
		
		Logger.debug("Songlist");
		Library.getSongList().print();
		
		Logger.debug("Playlist list");
		System.out.println(Library.getPlaylistList());
		for(Playlist playlist : Library.getPlaylistList()) {
			playlist.print();
			System.out.println();
		}
		
		Logger.debug("Album list");
		
		
		for(Album album : Library.getAlbumList()) {
			album.print();
			System.out.println();
		}
		
		
	//	launch(args);


		
//		Library.saveLibrary();
				
		
//		
	}
	
	
	public static void SetUpScene(Stage stage, Parent root) {
		final Scene scene = new Scene(root);
    	stage.sizeToScene();
        stage.setMinWidth(1080);
        stage.setMinHeight(720);
        stage.setTitle("My Application");
        stage.setScene(scene);
        stage.show();
}
}
