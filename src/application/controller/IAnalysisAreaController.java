/**
 * 
 */
package application.controller;


import application.event.NGSEPEvent;
import javafx.scene.Node;

/**
 * @author fernando
 * Interface used to implement all NGSEP analysis. 
 */
public interface IAnalysisAreaController {
	
	/**
	 * Initialize the component.
	 */
	public void initialize();
	
	/**
	 * Get the root node to display in the center area of the application.
	 * @return
	 */
	public Node getRootNode();
	
	/**
	 * Handle the NGSEPEvent received. 
	 * @param event
	 */
	public void handleNGSEPEvent(NGSEPEvent event);

}
