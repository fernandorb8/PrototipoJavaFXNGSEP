/**
 * 
 */
package application.controller;

import application.concurrent.NGSEPTask;
import application.view.ProgressBarComponent;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import ngsep.main.ProgressNotifier;

/**
 * Controller for the container of {@link ProgressBarComponent}. 
 * @author fernando
 */
public class ProgressBarAreaController {
	
	// Attributes.
	
	@FXML
	private VBox progressBarComponentContainerVBox;
	
	// Methods.
	
	/**
	 * Instantiates a new {@link ProgressBarController} and adds it's 
	 * {@link ProgressBarComponent} to the progress bar section of the 
	 * application.
	 * @return A {@link ProgressBarController} implementing 
	 * {@link ProgressNotifier}.
	 */
	public void getProgressNotifier(NGSEPTask<Void> task) {
		ProgressBarController progressBarController = new ProgressBarController();
		progressBarComponentContainerVBox.getChildren()
			.add(progressBarController.progressBarComponent);
		progressBarController.setTask(task);
	}
}
