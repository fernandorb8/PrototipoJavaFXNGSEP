/**
 * 
 */
package ngsepfx.event;

import java.io.File;

import javafx.event.EventType;

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
	
	public File file;
	
	// Constructor.
	
	/**
	 * Instantiate {@link NGSEPAnalyzeFileEvent} used to identify the 
	 * start of an analysis.
	 * @param controllerFullyQualifiedName To be used using reflection.
	 */
	public NGSEPAnalyzeFileEvent(String controllerFullyQualifiedName,
			File file) {
		super(NGSEPAnalyzeFileEvent.FILE);
		this.controllerFullyQualifiedName = controllerFullyQualifiedName;
		this.file = file;
	}

}
