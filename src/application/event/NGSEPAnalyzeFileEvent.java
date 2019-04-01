/**
 * 
 */
package application.event;

import javafx.event.EventType;

/**
 * @author fernando
 *
 */
public class NGSEPAnalyzeFileEvent extends NGSEPEvent {
	
	// Constants.
	
	public static final EventType<NGSEPAnalyzeFileEvent> FILE =
			new EventType<NGSEPAnalyzeFileEvent>(NGSEPEvent.ANY,"NGSEP_EVENT_TYPE_FILE");
	
	// Attributes.
	
	public String controllerFullyQualifiedName;
	

	/**
	 * 
	 */
	public NGSEPAnalyzeFileEvent(String controllerFullyQualifiedName) {
		super(NGSEPAnalyzeFileEvent.FILE);
		this.controllerFullyQualifiedName = controllerFullyQualifiedName;
	}

}
