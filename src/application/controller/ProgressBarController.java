/**
 * 
 */
package application.controller;

import application.view.ProgressBarComponent;
import javafx.scene.layout.VBox;
import ngsep.main.ProgressNotifier;

/**
 * The controller for the {@link ProgressBarComponent} responsible of 
 * communicating the NGSEP Analysis and the {@link ProgressBarComponent}
 * using the {@link ProgressNotifier} interface.
 * @author fernando
 * 
 */
public class ProgressBarController implements ProgressNotifier {
	
	// Attributes.
	
	public ProgressBarComponent progressBarComponent;
	
	// Constructor.
	
	/**
	 * Creates a new {@link ProgressBarComponent}
	 */
	public ProgressBarController() {
		this.progressBarComponent = new ProgressBarComponent();
	}	
	
	
	
    // ProgressNotifier.
    
	@Override
	public boolean keepRunning(int arg0) {
		progressBarComponent.setProgress(arg0/100);
		if(arg0 == 100 || !progressBarComponent.shouldKeepRunning) {
			VBox parent = (VBox) progressBarComponent.getParent();
			parent.getChildren().remove(progressBarComponent);
		}
		return progressBarComponent.shouldKeepRunning;
	}

}
