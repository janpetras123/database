package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * 
 * Vytvorenie primárného GUI rozhrania (okna).
 *
 */

public class Main extends Application {
	public static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			Main.primaryStage = primaryStage;
			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,800,533);
			primaryStage.setTitle("Info about moto racers and races");
			primaryStage.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("File1.css").toExternalForm());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
