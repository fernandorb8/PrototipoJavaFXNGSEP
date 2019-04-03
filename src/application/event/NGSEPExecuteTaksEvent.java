/**
 * 
 */
package application.event;

import java.util.concurrent.TimeUnit;

import javafx.event.EventType;

/**
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
	 * @param eventType
	 */
	public NGSEPExecuteTaksEvent(Runnable task) {
		super(EXECUTE_TASK);
		this.task = task;
	}

}
