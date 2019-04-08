/**
 * 
 */
package application.controller;

import application.view.ProgressBarComponent;
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
	public ProgressBarController getProgressNotifier() {
		ProgressBarController progressNotifier = new ProgressBarController();
		progressBarComponentContainerVBox.getChildren()
			.add(progressNotifier.progressBarComponent);
		return progressNotifier;
	}
}
