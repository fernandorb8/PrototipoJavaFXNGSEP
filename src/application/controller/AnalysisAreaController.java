/**
 * 
 */
package application.controller;


import java.io.IOException;
import java.net.URL;

import application.event.NGSEPEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * @author fernando
 * Interface used to implement all NGSEP analysis. 
 */
public abstract class AnalysisAreaController {
	
	// Attributes.
	
	@FXML
	private Node root;
	
	// Methods.
	
	/**
	 * Initialize the component loading the .fxml file and setting the root.
	 */
	public void initializeController() {
		try {
			URL fxmlLocation = getFXMLResource();
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
			fxmlLoader.setController(this);
			this.root = (VBox) fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the root node to display in the center area of the application.
	 * @return
	 */
	public Node getRootNode(){
		return root;
	}
	
	/**
	 * Get the css for the analysis area to be added. If null then it doesn't
	 * have one.
	 * @return The css toExternalForm() or null if it doesn't have one.
	 */
	public abstract String getCSSExternalForm();
	
	/**
	 * Get the .fxml file .getResource() to load.
	 * @return
	 */
	public abstract URL getFXMLResource();
	
	/**
	 * Handle the NGSEPEvent received. 
	 * @param event
	 */
	public abstract void handleNGSEPEvent(NGSEPEvent event);

}
