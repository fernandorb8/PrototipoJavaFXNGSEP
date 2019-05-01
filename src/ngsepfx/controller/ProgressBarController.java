/**
 * 
 */
package ngsepfx.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import ngsep.main.ProgressNotifier;
import ngsepfx.event.NGSEPRefreshFileExplorerEvent;
import ngsepfx.view.component.ProgressBarComponent;

/**
 * The controller for the {@link ProgressBarComponent} responsible of 
 * communicating the NGSEP Analysis and the {@link ProgressBarComponent}
 * using the {@link ProgressNotifier} interface.
 * @author fernando
 * 
 */
public class ProgressBarController {
	
	// Attributes.
	
	public ProgressBarComponent progressBarComponent;
	
	private Task<Void> task;
	
	// Constructor.
	
	/**
	 * Creates a new {@link ProgressBarComponent}
	 */
	public ProgressBarController() {
		this.progressBarComponent = new ProgressBarComponent();
		this.progressBarComponent.shouldKeepRunning.addListener(
			new ChangeListener<Boolean>() {
				@Override
				public void changed(
						ObservableValue<? extends Boolean> observable, 
						Boolean oldValue,
						Boolean newValue) {
					if (!newValue.booleanValue()) {
						if (task!=null) {
							task.cancel();
						}
						VBox parent = (VBox) progressBarComponent.getParent();
						parent.fireEvent(new NGSEPRefreshFileExplorerEvent());
						parent.getChildren().remove(progressBarComponent);
					}	
				}
			}
		);
	}	
	
	// Methods.
	
	public void setTask(Task<Void> task) {
		this.task = task;
		progressBarComponent.taskProgressBarDoubleProperty()
			.bind(task.progressProperty());
		progressBarComponent.taskNameTextProperty().bind(task.titleProperty());
		progressBarComponent.taskFileNameTextProperty()
		.bind(task.messageProperty());
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			
			@Override
			public void handle(WorkerStateEvent event) {
				VBox parent = (VBox) progressBarComponent.getParent();
				parent.fireEvent(new NGSEPRefreshFileExplorerEvent());
				parent.getChildren().remove(progressBarComponent);
			}
		});
		
	}

}
