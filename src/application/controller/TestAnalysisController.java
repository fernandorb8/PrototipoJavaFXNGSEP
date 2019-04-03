/**
 * 
 */
package application.controller;

import java.net.URL;
import java.util.concurrent.TimeUnit;

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
		this.getRootNode().fireEvent(
				new NGSEPExecuteTaksEvent(
						this::task
						));
	}
	
	private void task() {
		try {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " starting task");
			TimeUnit.SECONDS.sleep(10);
			System.out.println(threadName + " ending task");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
