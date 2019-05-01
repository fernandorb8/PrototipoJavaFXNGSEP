/**
 * 
 */
package ngsepfx.event;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Parent {@link Event} of all {@link NGSEPEvent} events. Containing the
 * parent {@link EventType}.
 * @author fernando
 *
 */
@SuppressWarnings("serial")
public class NGSEPEvent extends Event {
	
	// Constants.
	
	public static final EventType<NGSEPEvent> ANY = 
			new EventType<NGSEPEvent>("NGSEP_EVENT_TYPE_ANY");

	// Constructor.
	
	/**
	 * Instantiate an {@link Event} of type {@link NGSEPEvent#ANY}
	 */
	public NGSEPEvent() {
		super(NGSEPEvent.ANY);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Instantiate an {@link Event} of the given {@link EventType}.
	 * @param eventType
	 */
	public NGSEPEvent(EventType<? extends NGSEPEvent> eventType) {
		super(eventType);
	}

}
