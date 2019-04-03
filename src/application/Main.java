package application;
	
import application.executor.ExecutorSingleton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	// Main
	
	public static void main(String[] args) {
		launch(args);
	}
	
	// Application methods.
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = FXMLLoader.load(getClass()
					.getResource("view/Main.fxml"));
			Scene scene = new Scene(root,1200,675);
			primaryStage.setScene(scene);			
			primaryStage.show();
			scene.getStylesheets().add(getClass()
					.getResource("application.css").toExternalForm());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() throws Exception {
		super.stop();
		ExecutorSingleton.stopExecutor();
	}
	
}
