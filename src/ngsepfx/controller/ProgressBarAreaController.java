/**
 * 
 */
package ngsepfx.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import ngsepfx.concurrent.NGSEPTask;
import ngsepfx.view.component.ProgressBarComponent;

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
	 * application. Then it binds to the {@link NGSEPTask} for progress
	 * updating.
	 * @return A {@link ProgressBarController} for the 
	 * {@link ProgressBarComponent}
	 */
	public void addProgressBarComponentForTask(NGSEPTask<Void> task) {
		ProgressBarController progressBarController = new ProgressBarController();
		progressBarComponentContainerVBox.getChildren()
			.add(progressBarController.progressBarComponent);
		progressBarController.setTask(task);
	}
}
