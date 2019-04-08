/**
 * 
 */
package application.event;

import java.util.concurrent.TimeUnit;

import application.controller.MainController;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * {@link Event} to execute a {@link Runnable} task.
 * @author fernando
 *
 */
@SuppressWarnings("serial")
public class NGSEPExecuteTaksEvent extends NGSEPEvent {
	
	// Constants.
	
	public static final EventType<NGSEPExecuteTaksEvent> EXECUTE_TASK =
			new EventType<NGSEPExecuteTaksEvent>(
					NGSEPEvent.ANY,"NGSEP_EVENT_TYPE_EXECUTE"
					);
		
	// Attributes.
		
	public Runnable task;

	// Methods.
	
	/**
	 * Creates an event for the {@link MainController} to execute the task.
	 * @param task to be executed.
	 */
	public NGSEPExecuteTaksEvent(Runnable task) {
		super(EXECUTE_TASK);
		this.task = task;
	}

}
