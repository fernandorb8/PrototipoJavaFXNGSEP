/**
 * 
 */
package application.controller;


import application.event.NGSEPEvent;
import javafx.scene.Node;
import javafx.scene.Scene;

/**
 * @author fernando
 * Interface used to implement all NGSEP analysis. 
 */
public interface IAnalysisAreaController {
	
	/**
	 * Initialize the component.
	 * @param scene The scene of the application to add css files and perform 
	 * other actions.
	 */
	public void initializeController(Scene scene);
	
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
	
	/**
	 * Remove css files and perform any other action.
	 */
	public void prepareForReplacement();

}
