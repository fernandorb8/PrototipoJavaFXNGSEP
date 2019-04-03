/*
    Debigulator - A batch compression utility
Copyright (C) 2003-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
/*
 * Modified for this specific software.
 */
package application.controller;

import java.util.concurrent.ExecutorService;

import application.controller.fileexplorer.FileExplorerController;
import application.event.NGSEPAnalyzeFileEvent;
import application.event.NGSEPExecuteTaksEvent;
import application.executor.ExecutorSingleton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class MainController {
	
	// Attributes.
	
	@FXML 
	private FileExplorerController fileExplorerController;
	
	@FXML 
	private ProgressBarController progressBarController;
	
	@FXML
	private BorderPane rootBorderPane;
	
	private AnalysisAreaController controller;
	
	// FXML Life cycle methods.
	
	@FXML
	private void initialize() {
		
		rootBorderPane.addEventHandler(NGSEPAnalyzeFileEvent.FILE
				, this::handleNGSEPAnalyzeFileEvent);
		rootBorderPane.addEventHandler(NGSEPExecuteTaksEvent.EXECUTE_TASK
				, this::handleNGSEPExecuteTaskEvent);
		
	}
	
	// Methods.
	
	/**
	 * Get controller to process analysis and load it into the scene.
	 * @param event
	 */
	private void handleNGSEPAnalyzeFileEvent(NGSEPAnalyzeFileEvent event) {
		try {
			if (controller != null && controller.getCSSExternalForm() != null) {
				rootBorderPane.getScene().getStylesheets()
					.remove(controller.getCSSExternalForm());				
			}
			controller = (AnalysisAreaController)
					Class.forName(
							event.controllerFullyQualifiedName
							).newInstance();
			controller.initializeController();
			if (controller.getCSSExternalForm() != null) {
				rootBorderPane.getScene().getStylesheets()
				.add(controller.getCSSExternalForm());
			}
			Node analysisAreaRoot = controller.getRootNode();
			BorderPane analysisArea = (BorderPane) rootBorderPane.getCenter();
			analysisArea.setCenter(analysisAreaRoot);
			controller.handleNGSEPEvent(event);
			
		} catch (InstantiationException | IllegalAccessException 
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Receive the task to be passed to the Executor.
	 * @param event
	 */
	private void handleNGSEPExecuteTaskEvent(NGSEPExecuteTaksEvent event) {
		ExecutorService executor = ExecutorSingleton.getExecutor();
		executor.submit(event.task);
	}

}
