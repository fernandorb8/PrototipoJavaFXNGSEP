/**
 * 
 */
package application.event;

import javafx.event.EventType;

/**
 * @author fernando
 *
 */
public class NGSEPRefreshFileExplorerEvent extends NGSEPEvent {
	
	public static final EventType<NGSEPRefreshFileExplorerEvent> REFRESH =
			new EventType<NGSEPRefreshFileExplorerEvent>(NGSEPEvent.ANY
					,"NGSEP_EVENT_TYPE_REFRESH");
	
	public NGSEPRefreshFileExplorerEvent() {
		super(NGSEPRefreshFileExplorerEvent.REFRESH);
	}
}
