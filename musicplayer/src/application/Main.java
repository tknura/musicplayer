package application;
	
import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import com.pl.configuration.Config;
import com.pl.musicManager.Player;
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
		launch(args);
//		List<Song> songs = new LinkedList<>();
//		Duration length = Duration.between(Instant.EPOCH, Instant.now());
//		
//		Explorer explorer = new Explorer("C:/Users/Błażej/Desktop/test");
//		explorer.exploreDirectories(".mp3");
//		for(File file : explorer.getFileList()) {
//			songs.add(new Song(null, null, null, length, 0, file.getAbsolutePath()));
//		};
		String[] directories = {"C:/Users/Błażej/Desktop/test"};
		String[] extensions = {".pdf", ".mp3", ".png"};
		Config config = new Config(directories, extensions);	
		
		Library.initializeLibrary();
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
