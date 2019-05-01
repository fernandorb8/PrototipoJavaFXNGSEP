/**
 * 
 */
package ngsepfx.controller;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ngsep.main.ProgressNotifier;
import ngsepfx.concurrent.NGSEPTask;
import ngsepfx.event.NGSEPAnalyzeFileEvent;
import ngsepfx.event.NGSEPEvent;
import ngsepfx.event.NGSEPExecuteTaksEvent;

/**
 * Test analysis to demonstrate the usage of {@link NGSEPExecuteTaksEvent}.
 * @author fernando
 *
 */
public class TestAnalysisController extends AnalysisAreaController {
	
	// Constantes.
	
	private static final String NOMBRE_TAREA = "Test Analysis";
	
	// Attributes.
	
	private String nombreArchivo;
	
	// AnalysisAreaController.

	@Override
	public URL getFXMLResource() {
		// TODO Auto-generated method stub
		return getClass()
				.getResource("/ngsepfx/view/TestAnalysis.fxml");
	}

	/* (non-Javadoc)
	 * @see ngsepfx.controller.IAnalysisAreaController#getCSSExternalForm()
	 */
	@Override
	public String getCSSExternalForm() {
		return null;
	}

	/* (non-Javadoc)
	 * @see ngsepfx.controller.IAnalysisAreaController#handleNGSEPEvent(ngsepfx.event.NGSEPEvent)
	 */
	@Override
	public void handleNGSEPEvent(NGSEPEvent event) {
		if (NGSEPAnalyzeFileEvent.class.isInstance(event)) {
			NGSEPAnalyzeFileEvent ngsepAnalyzeFileEvent
				= (NGSEPAnalyzeFileEvent) event;
			this.nombreArchivo = ngsepAnalyzeFileEvent.file.getName();
		}
	}
	
	// Methods.
	
	/**
	 * Create an {@link NGSEPExecuteTaksEvent} with the 
	 * {@link TestAnalysisController#task()} as the {@link Runnable}.
	 * @param actionEvent
	 */
	@FXML
	private void triggerNGSEPExecuteTaskEvent(ActionEvent actionEvent) {
		executeTask(new NGSEPTask<Void>() {
			
    	    		@Override 
    	    		public Void call() {
    	    			try {
    	    				updateMessage(TestAnalysisController.this.nombreArchivo);
    	    				updateTitle(TestAnalysisController.NOMBRE_TAREA);
    	    				executeCall(this);
    	    			} catch (InterruptedException e) {
    	    				// TODO Auto-generated catch block
    	    				System.out.println(
    	    						Thread.currentThread().getName() 
    	    						+ " task cancelled");
    	    			}
    	    			return null;
    	    		}
        		});
	}
	
	/**
	 * Simulate a call to an NGSEPCore process that receives a 
	 * {@link ProgressNotifier}.
	 * @param progressNotifier The {@link ProgressNotifier}
	 * @throws InterruptedException if the process is interrupted.
	 */
	private void executeCall(ProgressNotifier progressNotifier) 
			throws InterruptedException {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " starting task");
		progressNotifier.keepRunning(50);
		System.out.println(threadName + " progress updated");
		TimeUnit.SECONDS.sleep(10);
		System.out.println(threadName + " going to finish");
		progressNotifier.keepRunning(100);
		System.out.println(threadName + " ending task");
	}

}
