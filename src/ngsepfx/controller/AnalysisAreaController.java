/**
 * 
 */
package ngsepfx.controller;


import java.io.IOException;
import java.net.URL;

import javafx.concurrent.Task;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import ngsep.main.ProgressNotifier;
import ngsepfx.concurrent.NGSEPTask;
import ngsepfx.event.NGSEPAnalyzeFileEvent;
import ngsepfx.event.NGSEPEvent;
import ngsepfx.event.NGSEPExecuteTaksEvent;

/**
 * Abstract class used to implement all NGSEP analysis. Gives the root node
 * to be loaded into the {@link Scene} and handles {@link NGSEPEvent}. The root 
 * is loaded from an .fxml.
 * @author fernando
 */
public abstract class AnalysisAreaController {
	
	// Attributes.
	
	@FXML
	private Parent root;
	
	// Methods.
	
	/**
	 * Initialize the {@link AnalysisAreaController} loading the .fxml file and setting the root.
	 */
	public void initializeController() {
		try {
			URL fxmlLocation = getFXMLResource();
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
			fxmlLoader.setController(this);
			this.root = fxmlLoader.load();
			if (this.getCSSExternalForm() != null) {
				this.root.getStylesheets().add(this.getCSSExternalForm());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Error loading .fxml", e);
		}
	}
	
	/**
	 * Get the root node to display in the center area of the application.
	 * @return
	 */
	public Parent getRootNode(){
		return root;
	}
	
	/**
	 * Get the css for the analysis area to be added. If null then it doesn't
	 * have one.
	 * @return The css {@link URL#toExternalForm()} or null if it doesn't have one.
	 */
	public abstract String getCSSExternalForm();
	
	/**
	 * Get the .fxml file {@link Class#getResource(String)} to load.
	 * @return
	 */
	public abstract URL getFXMLResource();
	
	/**
	 * Handle the {@link NGSEPEvent} received. This is usually an 
	 * {@link NGSEPAnalyzeFileEvent} but is left with it's parent 
	 * {@link EventType} as parameter for future development. To handle the
	 * {@link NGSEPEvent} the extending controller must extract the 
	 * relevant information from the event and create an {@link NGSEPTask}
	 * which also serves as the {@link ProgressNotifier}. The created
	 * {@link NGSEPTask} must call {@link NGSEPTask#keepRunning(int)} to 
	 * update the progress in the GUI. The created {@link NGSEPTask} must then be 
	 * passed to {@link AnalysisAreaController#executeTask(Task)}.
	 * @param event The {@link NGSEPEvent} to be processed.
	 */
	public abstract void handleNGSEPEvent(NGSEPEvent event);
	
	public void executeTask(NGSEPTask<Void> task) {
		getRootNode().fireEvent(
				new NGSEPExecuteTaksEvent(task));
	}

}
