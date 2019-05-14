/**
 * 
 */
package ngsepfx.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import ngsep.vcf.VCFSummaryStatisticsCalculator;
import ngsepfx.concurrent.NGSEPTask;
import ngsepfx.controller.validator.ValidationError;
import ngsepfx.controller.validator.ValidationErrorUtils;
import ngsepfx.event.NGSEPAnalyzeFileEvent;
import ngsepfx.event.NGSEPEvent;
import ngsepfx.view.component.ValidatedTextField;

/**
 * @author fernando
 *
 */
public class VCFSummaryStatisticsController extends AnalysisAreaController {
	
	//Constants.
	
	public static final String TASK_NAME = "VCF Summary Statistics";
	
	//FXML parameters.
	
	@FXML
	private ValidatedTextField inputVCFFileValidatedTextField;
	
	@FXML
	private ValidatedTextField outputFileValidatedTextField;
	
	@FXML
	private ValidatedTextField minimumSamplesValidatedTextField;
	
	//AnalysisAreaController.

	/* (non-Javadoc)
	 * @see ngsepfx.controller.AnalysisAreaController#getCSSExternalForm()
	 */
	@Override
	public String getCSSExternalForm() {
		return getClass()
				.getResource("/ngsepfx/view/vcfsummarystatistics.css")
				.toExternalForm();
	}

	/* (non-Javadoc)
	 * @see ngsepfx.controller.AnalysisAreaController#getFXMLResource()
	 */
	@Override
	public URL getFXMLResource() {
		return getClass()
				.getResource("/ngsepfx/view/VCFSummaryStatistics.fxml");
	}

	/* (non-Javadoc)
	 * @see ngsepfx.controller.AnalysisAreaController
	 * #handleNGSEPEvent(ngsepfx.event.NGSEPEvent)
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
	    			try (PrintStream out = new PrintStream(outputFileValidatedTextField.getText())) {
	    				updateMessage(inputVCFFileValidatedTextField.getText());
	    				updateTitle(VCFSummaryStatisticsController.TASK_NAME);
	    				VCFSummaryStatisticsCalculator instance = new VCFSummaryStatisticsCalculator();
	    				instance.setProgressNotifier(this);
	    				instance.setMinSamplesGenotyped(
	    						Integer.parseInt(
	    								minimumSamplesValidatedTextField.getText()));
	    				instance.runStatistics(
	    						inputVCFFileValidatedTextField.getText(), 
	    						out);
	    			} catch (IOException e) {
	    				System.out.println(
	    						Thread.currentThread().getName() 
	    						+ " could not open one of the files " 
	    						+ inputVCFFileValidatedTextField.getText()
	    						+ outputFileValidatedTextField.getText());
	    			} 
	    			return null;
	    		}
			});
		} 
	}
	
	private boolean validateFields() {
		ArrayList<ValidationError> errorsArray = new ArrayList<>();
		
		ControllerUtils.defaultValidatedTextFieldValidation(
				inputVCFFileValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				outputFileValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				minimumSamplesValidatedTextField, errorsArray);
		
		if(!errorsArray.isEmpty()) {
			ControllerUtils.showDefaultErrorDialog(
					ValidationErrorUtils.toHierarchichalString(errorsArray));
			return false;
		}		
		return true;
	}
	

}
