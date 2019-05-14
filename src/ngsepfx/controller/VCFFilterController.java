/**
 * 
 */
package ngsepfx.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
		if (this.validateFields()) {
			
		} 			
	}
	
	//Class methods.

	private boolean validateFields() {
		boolean areFieldsValid = true;
		ArrayList<ValidationError> errorsArray = new ArrayList<>();
		
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				fileValidatedTextField, errorsArray);
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				outputFileValidatedTextField, errorsArray);
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				filterRegionsFileValidatedTextField, errorsArray);
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				selectRegionsFileValidatedTextField, errorsArray);
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				minDistanceVariantsValidatedTextField, errorsArray);
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				minMAFValidatedTextField, errorsArray);
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				maxMAFValidatedTextField, errorsArray);
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				minHeterozygosityValidatedTextField, errorsArray);
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				maxHeterozygosityValidatedTextField, errorsArray);
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				minSamplesGenotypedValidatedTextField, errorsArray);
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				maxSamplesCNVSValidatedTextField, errorsArray);
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				minQualityValidatedTextField, errorsArray);
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				minCoverageValidatedTextField, errorsArray);
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				referenceFileValidatedTextField, errorsArray);
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				minGCContentValidatedTextField, errorsArray);
		areFieldsValid = Validator.defaultValidatedTextFieldValidation(
				maxGCContentValidatedTextField, errorsArray);
		
		ValidationError error = Validator.validate(
				sampleSelectionInput.getValidators()
				, sampleSelectionInput.getText()
				, sampleSelectionComboBox.getValue());
		if (error != null) {
			sampleSelectionInput.getStyleClass().add("error");
			errorsArray.add(error);
			areFieldsValid = false;
		} else {
			sampleSelectionInput.getStyleClass().remove("error");
		}
		
		if(!errorsArray.isEmpty()) {
			ControllerUtils.showDefaultErrorDialog(
					ValidationErrorUtils.toHierarchichalString(errorsArray));
		}
		
		return areFieldsValid;
	}

}
