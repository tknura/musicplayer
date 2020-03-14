package application;
	
import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import com.pl.configuration.Config;
import com.pl.musicManager.Album;
import com.pl.musicManager.Artist;
import com.pl.musicManager.Player;
import com.pl.musicManager.Playlist;
import com.pl.musicManager.Song;
import com.pl.musicManager.management.Library;
import com.pl.utility.Explorer;
import com.pl.utility.FontLoader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
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
//		launch(args);
//		List<Song> songs = new LinkedList<>();
//		Duration length = Duration.between(Instant.EPOCH, Instant.now());
//		
//		Explorer explorer = new Explorer("C:/Users/Błażej/Desktop/test");
//		explorer.exploreDirectories(".mp3");
//		for(File file : explorer.getFileList()) {
//			songs.add(new Song(null, null, null, length, 0, file.getAbsolutePath()));
//		};
		String[] directories = {"C:\\Java Projects\\musicplayer\\Taco Hemingway - Cafe Belga [mp3]"};
		String[] extensions = {".mp3"};
		Config config = new Config(directories, extensions);	
		Library.initializeLibrary();
		
		
//		Song song1 = Library.getSongList().getSong(0);
//		Song song2 = Library.getSongList().getSong(1);
//		Song song3 = Library.getSongList().getSong(2);
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
//		Album al1 = new Album(10, "Hollywood is bleeding", "Post Malone", 2019, null);
//		al1.add(song1);
//		al1.add(song2);
//		al1.add(song3);
//		
//		Album al2 = new Album(20, "Schodki", "Mata", 2020, null);
//		al2.add(song1);
//		al2.add(song2);
//		al2.add(song3);
//		
//		Artist ar1 = new Artist("Post Malone");
//		Artist ar2 = new Artist("Mata");
//		
//		ar1.addAlbum(al1);
//		ar1.addAlbum(al2);
//		
//		ar2.addAlbum(al1);
//		ar2.addAlbum(al2);
//		
//		Library.addArtist(ar1);
//		Library.addArtist(ar2);
//		Library.addAlbum(al1);
//		Library.addPlaylist(pl1);
//		Library.addPlaylist(pl2);
//		
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
