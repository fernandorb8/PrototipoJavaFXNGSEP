/**
 * 
 */
package application.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;

import application.concurrent.NGSEPTask;
import application.controller.validator.Validator;
import application.event.NGSEPAnalyzeFileEvent;
import application.event.NGSEPEvent;
import application.view.component.ValidatedTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import ngsep.vcf.VCFSummaryStatisticsCalculator;

/**
 * @author fernando
 *
 */
public class VCFSummaryStatistics extends AnalysisAreaController {
	
	//Constants.
	
	public static final String TASK_NAME = "VCF Summary Statistics";
	
	//Parameters.
	
	private StringBuilder errorsMessage = new StringBuilder(); 
	
	//FXML parameters.
	
	@FXML
	private ValidatedTextField inputVCFFileValidatedTextField;
	
	@FXML
	private ValidatedTextField outputFileValidatedTextField;
	
	@FXML
	private ValidatedTextField minimumSamplesValidatedTextField;
	
	//AnalysisAreaController.

	/* (non-Javadoc)
	 * @see application.controller.AnalysisAreaController#getCSSExternalForm()
	 */
	@Override
	public String getCSSExternalForm() {
		return getClass()
				.getResource("/application/view/vcfsummarystatistics.css")
				.toExternalForm();
	}

	/* (non-Javadoc)
	 * @see application.controller.AnalysisAreaController#getFXMLResource()
	 */
	@Override
	public URL getFXMLResource() {
		return getClass()
				.getResource("/application/view/VCFSummaryStatistics.fxml");
	}

	/* (non-Javadoc)
	 * @see application.controller.AnalysisAreaController
	 * #handleNGSEPEvent(application.event.NGSEPEvent)
	 */
	@Override
	public void handleNGSEPEvent(NGSEPEvent event) {
		NGSEPAnalyzeFileEvent analyzeEvent = (NGSEPAnalyzeFileEvent) event;
		File file = analyzeEvent.file;
		inputVCFFileValidatedTextField.setText(file.getAbsolutePath());
		String dir = file.getParent();
		int extensionIndex = file.getName().lastIndexOf('.');
		String extensionlessName = file.getName().substring(0, extensionIndex);
		String outputFileAbsolutePath = dir + File.separator + extensionlessName 
				+ "_SummaryStats.txt";
		outputFileValidatedTextField.setText(outputFileAbsolutePath);
		minimumSamplesValidatedTextField.setText("20");
	}
	
	//FXML Methods.
	
	@FXML
	private void changeInputVCFFile(ActionEvent actionEvent) {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(System.getProperty("user.home")));
		chooser.setTitle("Select VCF input file");
		File selectedFile = chooser.showOpenDialog(null);
		if (selectedFile != null) {
			inputVCFFileValidatedTextField.setText(selectedFile.getAbsolutePath());
		}
	}
	
	@FXML
	private void changeOutputFile(ActionEvent actionEvent) {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setInitialDirectory(new File(System.getProperty("user.home")));
		chooser.setTitle("Select output directory");
		File selectedDirectory = chooser.showDialog(null);
		if (selectedDirectory != null) {
			File file = new File(inputVCFFileValidatedTextField.getText());
			int extensionIndex = file.getName().lastIndexOf('.');
			String extensionlessName = file.getName().substring(0, extensionIndex);
			String newFileName = selectedDirectory.getAbsolutePath() 
					+ File.separator + extensionlessName + "_SummaryStats.txt";
			outputFileValidatedTextField.setText(newFileName);
		}
	}
	
	@FXML
	private void submit(ActionEvent actionEvent) {
		if (validateFields()) {		
			executeTask(new NGSEPTask<Void>() {
				
	    		@Override 
	    		public Void call() {
	    			PrintStream out = null;
	    			try {
	    				updateMessage(inputVCFFileValidatedTextField.getText());
	    				updateTitle(VCFSummaryStatistics.TASK_NAME);
	    				VCFSummaryStatisticsCalculator instance = new VCFSummaryStatisticsCalculator();
	    				instance.setProgressNotifier(this);
	    				instance.setMinSamplesGenotyped(
	    						Integer.parseInt(
	    								minimumSamplesValidatedTextField.getText()));
	    				out = new PrintStream(outputFileValidatedTextField.getText());
	    				instance.runStatistics(
	    						inputVCFFileValidatedTextField.getText(), 
	    						out);
	    			} catch (IOException e) {
	    				System.out.println(
	    						Thread.currentThread().getName() 
	    						+ " could not open one of the files " 
	    						+ inputVCFFileValidatedTextField.getText()
	    						+ outputFileValidatedTextField.getText());
	    			} finally {
	    				if (out != null) {
	    					out.flush();
	    					out.close();
	    				}
					}
	    			return null;
	    		}
			});
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			
			TextArea textArea = new TextArea(errorsMessage.toString());
			textArea.setEditable(false);
			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);
			
			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(textArea, 0, 0);

			alert.setTitle("Error");
			alert.setHeaderText("Errors in one or more fields");
			alert.setContentText("Errors in one or more fields");
			alert.initModality(Modality.NONE);
			alert.getDialogPane().setExpandableContent(expContent);		
			

			alert.show();
			
			errorsMessage = new StringBuilder();
		}
	}
	
	private boolean validateFields() {
		boolean areFieldsValid = true;
		boolean isFieldValid;
		isFieldValid = Validator.validate(inputVCFFileValidatedTextField.getValidators()
				, inputVCFFileValidatedTextField.getText(), errorsMessage
				, inputVCFFileValidatedTextField.getLabel().getText());
		areFieldsValid = areFieldsValid && isFieldValid;
		isFieldValid = Validator.validate(outputFileValidatedTextField.getValidators()
				, outputFileValidatedTextField.getText(), errorsMessage
				, outputFileValidatedTextField.getLabel().getText());
		areFieldsValid = areFieldsValid && isFieldValid;
		isFieldValid = Validator.validate(minimumSamplesValidatedTextField.getValidators()
				, minimumSamplesValidatedTextField.getText(), errorsMessage
				, minimumSamplesValidatedTextField.getLabel().getText());
		areFieldsValid = areFieldsValid && isFieldValid;
		
		return areFieldsValid;
	}
	

}
