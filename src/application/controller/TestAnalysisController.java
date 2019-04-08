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
import ngsep.main.ProgressNotifier;

/**
 * Test analysis to demonstrate the usage of {@link NGSEPExecuteTaksEvent}.
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
		return null;
	}

	/* (non-Javadoc)
	 * @see application.controller.IAnalysisAreaController#handleNGSEPEvent(application.event.NGSEPEvent)
	 */
	@Override
	public void handleNGSEPEvent(NGSEPEvent event, 
			ProgressBarController progressBarController) {
		this.progressBarController = progressBarController;

	}
	
	// Methods.
	
	/**
	 * Create an {@link NGSEPExecuteTaksEvent} with the 
	 * {@link TestAnalysisController#task()} as the {@link Runnable}.
	 * @param actionEvent
	 */
	@FXML
	private void triggerNGSEPExecuteTaskEvent(ActionEvent actionEvent) {
		this.getRootNode().fireEvent(
				new NGSEPExecuteTaksEvent(
						this::task
						));
	}
	
	/**
	 * {@link Runnable} for the {@link NGSEPExecuteTaksEvent}.
	 */
	private void task() {
		try {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " starting task");
			progressBarController.keepRunning(50);
			System.out.println(threadName + " progress updated");
			TimeUnit.SECONDS.sleep(10);
			System.out.println(threadName + " going to finish");
			progressBarController.keepRunning(100);
			System.out.println(threadName + " ending task");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
