/**
 * 
 */
package ngsepfx.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import ngsep.genome.ReferenceGenome;
import ngsep.vcf.VCFFilter;
import ngsepfx.concurrent.NGSEPTask;
import ngsepfx.controller.validator.ValidationError;
import ngsepfx.controller.validator.ValidationErrorUtils;
import ngsepfx.controller.validator.Validator;
import ngsepfx.event.NGSEPAnalyzeFileEvent;
import ngsepfx.event.NGSEPEvent;
import ngsepfx.view.component.ValidatedTextField;

/**
 * @author fernando
 *
 */
public class VCFFilterController extends AnalysisAreaController {
	
	//Constants.
	
	private static final String SUFFIX = "_filter.vcf";
	
	private static final String TASK_NAME = "VCF Filter";
	
	private static final String REFERENCE_GENOME_TASK_NAME = "Loading reference genome";
	
	//FXML Attributes.
		
		//Main Arguments
	
	@FXML
	private ValidatedTextField fileValidatedTextField;
	
	@FXML
	private ValidatedTextField outputFileValidatedTextField;
	
	@FXML
	private ValidatedTextField filterRegionsFileValidatedTextField;
	
	@FXML
	private ValidatedTextField selectRegionsFileValidatedTextField;
	
	@FXML
	private ValidatedTextField minDistanceVariantsValidatedTextField;
	
	@FXML
	private ValidatedTextField minMAFValidatedTextField;
	
	@FXML
	private ValidatedTextField maxMAFValidatedTextField;
	
	@FXML
	private ValidatedTextField minHeterozygosityValidatedTextField;
	
	@FXML
	private ValidatedTextField maxHeterozygosityValidatedTextField;
	
	@FXML
	private ValidatedTextField minSamplesGenotypedValidatedTextField;
	
	@FXML
	private ValidatedTextField maxSamplesCNVSValidatedTextField;
	
	@FXML
	private ValidatedTextField minQualityValidatedTextField;
	
	@FXML
	private ValidatedTextField minCoverageValidatedTextField;
	
	@FXML
	private CheckBox keepBiAllelicSNVsCheckBox;
	
	@FXML
	private CheckBox filterInvariantSitesCheckBox;
	
	@FXML
	private CheckBox filterInvariantAlternativeCheckBox;
	
	@FXML
	private CheckBox filterInvariantReferenceCheckBox;
	
		//GC Content Filter
	
	@FXML
	private ValidatedTextField referenceFileValidatedTextField;
	
	@FXML
	private ValidatedTextField minGCContentValidatedTextField;
	
	@FXML 
	private ValidatedTextField maxGCContentValidatedTextField;
	
		//Functional Filter
	
	@FXML
	private ValidatedTextField geneNameValidatedTextField;
	
	@FXML 
	private CheckBox synonymousVariantCheckbox;
	
	@FXML 
	private CheckBox missenseVariantCheckbox;
	
	@FXML 
	private CheckBox stopLostCheckbox;
	
	@FXML 
	private CheckBox stopGainedCheckbox;
	
	@FXML 
	private CheckBox startLostCheckbox;
	
	@FXML 
	private CheckBox inframeDeletionCheckbox;
	
	@FXML 
	private CheckBox inframeInsertionCheckbox;
	
	@FXML 
	private CheckBox frameshiftVariantCheckbox;
	
	@FXML 
	private CheckBox spliceDonorVariantCheckbox;
	
	@FXML 
	private CheckBox spliceAcceptorVariantCheckbox;
	
	@FXML 
	private CheckBox exonicSpliceRegionVariantCheckbox;
	
	@FXML 
	private CheckBox spliceRegionVariantCheckbox;
	
	@FXML 
	private CheckBox FivePrimeUTRVariantCheckbox;
	
	@FXML 
	private CheckBox ThreePrimeUTRVariantCheckbox;
	
	@FXML 
	private CheckBox nonCodingTranscriptExonVariantCheckbox;
	
	@FXML 
	private CheckBox upstreamTranscriptVariantCheckbox;
	
	@FXML 
	private CheckBox downstreamTranscriptVariantCheckbox;
	
	@FXML 
	private CheckBox intronVariantCheckbox;
	
	@FXML 
	private CheckBox intergenicVariantCheckbox;
	
		//Sample selection.
	
	@FXML
	private ComboBox<String> sampleSelectionComboBox;
	
	@FXML
	private ValidatedTextField sampleSelectionInput;
	
	

	/* (non-Javadoc)
	 * @see ngsepfx.controller.AnalysisAreaController#getCSSExternalForm()
	 */
	@Override
	public String getCSSExternalForm() {
		// TODO Auto-generated method stub
		return getClass().getResource("/ngsepfx/view/vcffilter.css")
				.toExternalForm();
	}

	/* (non-Javadoc)
	 * @see ngsepfx.controller.AnalysisAreaController#getFXMLResource()
	 */
	@Override
	public URL getFXMLResource() {
		// TODO Auto-generated method stub
		return getClass().getResource("/ngsepfx/view/VCFFilter.fxml");
	}

	/* (non-Javadoc)
	 * @see ngsepfx.controller.AnalysisAreaController#handleNGSEPEvent(ngsepfx.event.NGSEPEvent)
	 */
	@Override
	public void handleNGSEPEvent(NGSEPEvent event) {
		NGSEPAnalyzeFileEvent ngsepAnalyzeFileEvent = 
				(NGSEPAnalyzeFileEvent) event;
		File file = ngsepAnalyzeFileEvent.file;
		fileValidatedTextField.setText(file.getAbsolutePath());
		String dir = file.getParent();
		int extensionIndex = file.getName().lastIndexOf('.');
		String extensionlessName = file.getName().substring(0, extensionIndex);
		String outputFileAbsolutePath = dir + File.separator + extensionlessName 
				+ SUFFIX;
		outputFileValidatedTextField.setText(outputFileAbsolutePath);
		minDistanceVariantsValidatedTextField.setText("0");
		minMAFValidatedTextField.setText("0.0");
		maxMAFValidatedTextField.setText("0.5");
		minHeterozygosityValidatedTextField.setText("0.0");
		maxHeterozygosityValidatedTextField.setText("1.0");
		minSamplesGenotypedValidatedTextField.setText("1");
		minQualityValidatedTextField.setText("40");
		minCoverageValidatedTextField.setText("1");
		minGCContentValidatedTextField.setText("40.0");
		maxGCContentValidatedTextField.setText("65.0");
	}
	
	//FXML methods
	
	@FXML
	private void changeFileInput(ActionEvent event) {
		//https://stackoverflow.com/questions/37902660/javafx-button-sending-arguments-to-actionevent-function
		Node node = (Node) event.getSource() ;
		ValidatedTextField validatedTextField = (ValidatedTextField)
				node.getUserData();
		ControllerUtils.changeFileInput(validatedTextField, validatedTextField.getLabel()
				.getText());
	}
	
	@FXML
	private void changeFileInputSampleSelection(ActionEvent event) {
		//https://stackoverflow.com/questions/37902660/javafx-button-sending-arguments-to-actionevent-function
		Node node = (Node) event.getSource() ;
		ValidatedTextField validatedTextField = (ValidatedTextField)
				node.getUserData();
		ControllerUtils.changeFileInput(validatedTextField, 
				sampleSelectionComboBox.getValue());
	}
	
	@FXML
	private void changeFileOutput(ActionEvent event) {
		Node node = (Node) event.getSource() ;
		ValidatedTextField validatedTextField = (ValidatedTextField)
				node.getUserData();
		File file = new File(fileValidatedTextField.getText());
		ControllerUtils.changeFileOutput(validatedTextField, file.getName(), SUFFIX);
	}

	@FXML
	private void startVCFFilter(ActionEvent event) {
		if (validateFields()) {
				executeTask(new NGSEPTask<Void>() {
				
	    		@Override 
	    		public Void call() {
	    			try (PrintStream out = new PrintStream(outputFileValidatedTextField.getText())) {
	    				
	    				//Progress bar info.
	    				updateMessage(fileValidatedTextField.getText());
	    				updateTitle(TASK_NAME);
	    				
	    				
	    				//NGSEPCore call.
	    				VCFFilter instance = new VCFFilter();
	    				
	    				//Generic attributes.
	    				instance.setProgressNotifier(this);
	    				
	    				//Specific attributes.
	    					
	    					//Main Arguments.
	    					
	    				if (!filterRegionsFileValidatedTextField.getText().trim().isEmpty()) {
	    					instance.setRegionsToFilter(filterRegionsFileValidatedTextField.getText());
	    				}
	    				if (!selectRegionsFileValidatedTextField.getText().trim().isEmpty()) {
	    					instance.setRegionsToSelect(selectRegionsFileValidatedTextField.getText());
	    				}
	    				if (!minDistanceVariantsValidatedTextField.getText().trim().isEmpty()) {
	    					instance.setMinDistance(Integer.parseInt(minDistanceVariantsValidatedTextField.getText()));
	    				}
	    				if (!minMAFValidatedTextField.getText().trim().isEmpty()) {
	    					instance.setMinMAF(Double.parseDouble(minMAFValidatedTextField.getText()));
	    				}
	    				if (!maxMAFValidatedTextField.getText().trim().isEmpty()) {
	    					instance.setMaxMAF(Double.parseDouble(maxMAFValidatedTextField.getText()));
	    				}
	    				if (!minHeterozygosityValidatedTextField.getText().trim().isEmpty()) {
	    					instance.setMinOH(Double.parseDouble(minHeterozygosityValidatedTextField.getText()));
	    				}
	    				if (!maxHeterozygosityValidatedTextField.getText().trim().isEmpty()) {
	    					instance.setMaxOH(Double.parseDouble(maxHeterozygosityValidatedTextField.getText()));
	    				}
	    				if (!minSamplesGenotypedValidatedTextField.getText().trim().isEmpty()) {
	    					instance.setMinIndividualsGenotyped(Integer.parseInt(minSamplesGenotypedValidatedTextField.getText()));
	    				}
	    				if (!maxSamplesCNVSValidatedTextField.getText().trim().isEmpty()) {
	    					instance.setMaxCNVs(Integer.parseInt(maxSamplesCNVSValidatedTextField.getText()));
	    				}
	    				if (!minQualityValidatedTextField.getText().trim().isEmpty()) {
	    					instance.setMinGenotypeQuality(Integer.parseInt(minQualityValidatedTextField.getText()));
	    				}
	    				if (!minCoverageValidatedTextField.getText().trim().isEmpty()) {
	    					instance.setMinCoverage(Integer.parseInt(minQualityValidatedTextField.getText()));
	    				}
	    				instance.setKeepOnlySNVs(keepBiAllelicSNVsCheckBox.isSelected());
	    				instance.setFilterInvariant(filterInvariantSitesCheckBox.isSelected());
	    				instance.setFilterInvariantAlternative(filterInvariantAlternativeCheckBox.isSelected());
	    				instance.setFilterInvariantReference(filterInvariantReferenceCheckBox.isSelected());
	    				
	    					//Functional Filter.	    				

	    				if (!geneNameValidatedTextField.getText().trim().isEmpty()) {
	    					instance.setGeneId(geneNameValidatedTextField.getText());
	    				}
	    				Set<String>annotations = new TreeSet<String>();
	    				if(synonymousVariantCheckbox.isSelected()) {
	    					annotations.add(synonymousVariantCheckbox.getText());
	    				}
	    				if(missenseVariantCheckbox.isSelected()) {
	    					annotations.add(missenseVariantCheckbox.getText());
	    				}
	    				if(stopLostCheckbox.isSelected()) {
	    					annotations.add(stopLostCheckbox.getText());
	    				}
	    				if(stopGainedCheckbox.isSelected()) {
	    					annotations.add(stopGainedCheckbox.getText());
	    				}
	    				if(startLostCheckbox.isSelected()) {
	    					annotations.add(startLostCheckbox.getText());
	    				}
	    				if(inframeDeletionCheckbox.isSelected()) {
	    					annotations.add(inframeDeletionCheckbox.getText());
	    				}
	    				if(inframeInsertionCheckbox.isSelected()) {
	    					annotations.add(inframeInsertionCheckbox.getText());
	    				}
	    				if(frameshiftVariantCheckbox.isSelected()) {
	    					annotations.add(frameshiftVariantCheckbox.getText());
	    				}
	    				if(spliceDonorVariantCheckbox.isSelected()) {
	    					annotations.add(spliceDonorVariantCheckbox.getText());
	    				}
	    				if(spliceAcceptorVariantCheckbox.isSelected()) {
	    					annotations.add(spliceAcceptorVariantCheckbox.getText());
	    				}
	    				if(exonicSpliceRegionVariantCheckbox.isSelected()) {
	    					annotations.add(exonicSpliceRegionVariantCheckbox.getText());
	    				}
	    				if(spliceRegionVariantCheckbox.isSelected()) {
	    					annotations.add(spliceRegionVariantCheckbox.getText());
	    				}
	    				if(FivePrimeUTRVariantCheckbox.isSelected()) {
	    					annotations.add(FivePrimeUTRVariantCheckbox.getText());
	    				}
	    				if(ThreePrimeUTRVariantCheckbox.isSelected()) {
	    					annotations.add(ThreePrimeUTRVariantCheckbox.getText());
	    				}
	    				if(nonCodingTranscriptExonVariantCheckbox.isSelected()) {
	    					annotations.add(nonCodingTranscriptExonVariantCheckbox.getText());
	    				}
	    				if(upstreamTranscriptVariantCheckbox.isSelected()) {
	    					annotations.add(upstreamTranscriptVariantCheckbox.getText());
	    				}
	    				if(downstreamTranscriptVariantCheckbox.isSelected()) {
	    					annotations.add(downstreamTranscriptVariantCheckbox.getText());
	    				}
	    				if(intronVariantCheckbox.isSelected()) {
	    					annotations.add(intronVariantCheckbox.getText());
	    				}
	    				if(intergenicVariantCheckbox.isSelected()) {
	    					annotations.add(intergenicVariantCheckbox.getText());
	    				}
	    				if(annotations.size()>0) instance.setAnnotations(annotations);
	    				
	    					//Sample Selection.
	    				
	    				if (!sampleSelectionInput.getText().trim().isEmpty()) {
	    					instance.setSampleIds(sampleSelectionInput.getText());
	    					instance.setFilterSamples(sampleSelectionComboBox.getSelectionModel().getSelectedIndex()==0);
	    				}
	    				
	    					//GC Content Filter.
	    				
	    				if(!referenceFileValidatedTextField.getText().trim().isEmpty()) {
	    					//Progress bar info.
		    				updateMessage(referenceFileValidatedTextField.getText());
		    				updateTitle(TASK_NAME + ":" + REFERENCE_GENOME_TASK_NAME);
	    					
	    					ReferenceGenome genome = new ReferenceGenome(referenceFileValidatedTextField.getText());
		    				instance.setGenome(genome);
	    					
	    					//Fake progress.
		    				this.keepRunning(10);
		    				
		    				if(!minGCContentValidatedTextField.getText().trim().isEmpty()) {
		    					instance.setMinGCContent(Double.parseDouble(minGCContentValidatedTextField.getText()));
		    				}
		    				if(!maxGCContentValidatedTextField.getText().trim().isEmpty()) {
		    					instance.setMaxGCContent(Double.parseDouble(maxGCContentValidatedTextField.getText()));
		    				}
	    				}
	    				
	    				//Progress bar info.
	    				updateMessage(fileValidatedTextField.getText());
	    				updateTitle(TASK_NAME);
	    				
	    				//Start analysis.
	    				instance.processVariantsFile(fileValidatedTextField.getText(), out);
	    				
	    			}
	    			catch (IOException e) {
	    				throw new RuntimeException(Thread.currentThread().getName() 
	    						+ " could not open one of the files");
	    			} 
	    			return null;
	    		}
			});
		} 			
	}
	
	//Class methods.

	private boolean validateFields() {
		ArrayList<ValidationError> errorsArray = new ArrayList<>();
		
		ControllerUtils.defaultValidatedTextFieldValidation(
				fileValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				outputFileValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				filterRegionsFileValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				selectRegionsFileValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				minDistanceVariantsValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				minMAFValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				maxMAFValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				minHeterozygosityValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				maxHeterozygosityValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				minSamplesGenotypedValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				maxSamplesCNVSValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				minQualityValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				minCoverageValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				referenceFileValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				minGCContentValidatedTextField, errorsArray);
		ControllerUtils.defaultValidatedTextFieldValidation(
				maxGCContentValidatedTextField, errorsArray);
		
		ValidationError error = Validator.validate(
				sampleSelectionInput.getValidators()
				, sampleSelectionInput.getText()
				, sampleSelectionComboBox.getValue());
		if (error != null) {
			sampleSelectionInput.getStyleClass().add("error");
			errorsArray.add(error);
		} else {
			sampleSelectionInput.getStyleClass().remove("error");
		}
		
		if(!errorsArray.isEmpty()) {
			ControllerUtils.showDefaultErrorDialog(
					ValidationErrorUtils.toHierarchichalString(errorsArray));
			return false;
		}		
		return true;
	}

}
