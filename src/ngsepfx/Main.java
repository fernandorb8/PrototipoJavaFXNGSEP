package ngsepfx;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ngsepfx.concurrent.ExecutorSingleton;

/**
 * Main class of the application. Loads Main.fxml and application wide 
 * application.css. Sets Scene dimensions.
 * @author fernando
 *
 */
public class Main extends Application {
	
	// Main
	
	/**
	 * Launch the {@link Application}.
	 * @param args Not used.
	 */
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
					.getResource("/ngsepfx/view/application.css").toExternalForm());
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
