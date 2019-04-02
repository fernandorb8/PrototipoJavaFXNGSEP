/**
 * 
 */
package application.event;

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
		
	public String task;

	// Methods.
	
	/**
	 * @param eventType
	 */
	public NGSEPExecuteTaksEvent(String task) {
		super(EXECUTE_TASK);
		this.task = task;
	}

}
