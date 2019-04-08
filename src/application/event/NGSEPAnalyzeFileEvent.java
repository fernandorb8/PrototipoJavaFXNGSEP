/**
 * 
 */
package application.event;

import javafx.event.EventType;
import ngsep.main.ProgressNotifier;

/**
 * {@link NGSEPEvent} for file analysis.
 * @author fernando
 *
 */
@SuppressWarnings("serial")
public class NGSEPAnalyzeFileEvent extends NGSEPEvent {
	
	// Constants.
	
	public static final EventType<NGSEPAnalyzeFileEvent> FILE =
			new EventType<NGSEPAnalyzeFileEvent>(NGSEPEvent.ANY,"NGSEP_EVENT_TYPE_FILE");
	
	// Attributes.
	
	public String controllerFullyQualifiedName;
	
	// Constructor.
	
	/**
	 * Instantiate {@link NGSEPAnalyzeFileEvent} used to identify the 
	 * start of an analysis.
	 * @param controllerFullyQualifiedName To be used using reflection.
	 */
	public NGSEPAnalyzeFileEvent(String controllerFullyQualifiedName) {
		super(NGSEPAnalyzeFileEvent.FILE);
		this.controllerFullyQualifiedName = controllerFullyQualifiedName;
	}

}
