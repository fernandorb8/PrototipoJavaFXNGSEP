/**
 * 
 */
package application.event;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * @author fernando
 *
 */
public class NGSEPEvent extends Event {
	
	public static final EventType<NGSEPEvent> ANY = 
			new EventType<NGSEPEvent>("NGSEP_EVENT_TYPE_ANY");

	public NGSEPEvent() {
		super(NGSEPEvent.ANY);
		// TODO Auto-generated constructor stub
	}
	
	public NGSEPEvent(EventType<? extends NGSEPEvent> eventType) {
		super(eventType);
	}

}
