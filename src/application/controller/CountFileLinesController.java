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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * @author fernando
 *
 */
public class CountFileLinesController implements IAnalysisAreaController {
	
	// Attributes.
	
	@FXML
	private Text fileNameText;
	
	@FXML
	private Text numberLinesText;
	
	@FXML
	private StackPane root;
	
	// IAnalysisAreaController methods.

	@Override
	public void initialize() {
		try {
			URL fxmlLocation = getClass().getResource("/application/view/CountFileLinesAnalysis.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
			fxmlLoader.setController(this);
			this.root = (StackPane) fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see application.controller.IAnalysisAreaController#getRootNode()
	 */
	@Override
	public Node getRootNode() {
		return root;
	}

	/* (non-Javadoc)
	 * @see application.controller.IAnalysisAreaController#handleNGSEPEvent(application.event.NGSEPEvent)
	 */
	@Override
	public void handleNGSEPEvent(NGSEPEvent event) {
		// TODO Auto-generated method stub

	}

}
