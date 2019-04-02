/**
 * 
 */
package application.controller;

import java.net.URL;

import application.event.NGSEPEvent;
import application.event.NGSEPExecuteTaksEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * @author fernando
 *
 */
public class TestAnalysisController extends AnalysisAreaController {
	
	// Attributes.
	
	// AnalysisAreaController.

	@Override
	public URL getFXMLResource() {
		// TODO Auto-generated method stub
		return getClass()
				.getResource("/application/view/TestAnalysis.fxml");
	}

	/* (non-Javadoc)
	 * @see application.controller.IAnalysisAreaController#getCSSExternalForm()
	 */
	@Override
	public String getCSSExternalForm() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see application.controller.IAnalysisAreaController#handleNGSEPEvent(application.event.NGSEPEvent)
	 */
	@Override
	public void handleNGSEPEvent(NGSEPEvent event) {
		// TODO Auto-generated method stub

	}
	
	// Methods.
	
	@FXML
	private void triggerNGSEPExecuteTaskEvent(ActionEvent actionEvent) {
		this.getRootNode().fireEvent(new NGSEPExecuteTaksEvent(actionEvent
				.getTarget().toString()));
	}

}
