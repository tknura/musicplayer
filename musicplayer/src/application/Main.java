package application;
	


import com.pl.utility.configuration.Config;
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
		String[] directories = {};
		String[] extensions = {};
		Config.setDirectories(directories);
		Config.setExtensions(extensions);

		launch(args);


		
//		Library.saveLibrary();
				
		
//		
	}
	
	
	public static void SetUpScene(Stage stage, Parent root) {
		final Scene scene = new Scene(root);
    	stage.sizeToScene();
        stage.setMinWidth(1080);
        stage.setMinHeight(720);
        stage.setTitle("Musicplayer zajebisty fchuuuj");
        stage.setScene(scene);
        stage.show();
}
}
