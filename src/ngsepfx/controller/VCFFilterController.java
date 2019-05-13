/**
 * 
 */
package ngsepfx.controller;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import ngsepfx.event.NGSEPEvent;
import ngsepfx.view.component.ValidatedTextField;

/**
 * @author fernando
 *
 */
public class VCFFilterController extends AnalysisAreaController {
	
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
	private ValidatedTextField minHeterozygosityValidatedTextField;
	
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
		// TODO Auto-generated method stub

	}
	
	//FXML methods
	
	@FXML
	private void changeFileInput(ActionEvent event) {
		//https://stackoverflow.com/questions/37902660/javafx-button-sending-arguments-to-actionevent-function
		Node node = (Node) event.getSource() ;
		ValidatedTextField validatedTextField = (ValidatedTextField)
				node.getUserData();
		Utils.changeFileInput(validatedTextField, validatedTextField.getLabel()
				.getText());
	}
	
	@FXML
	private void changeFileInputSampleSelection(ActionEvent event) {
		//https://stackoverflow.com/questions/37902660/javafx-button-sending-arguments-to-actionevent-function
		Node node = (Node) event.getSource() ;
		ValidatedTextField validatedTextField = (ValidatedTextField)
				node.getUserData();
		Utils.changeFileInput(validatedTextField, 
				sampleSelectionComboBox.getValue());
	}
	
	@FXML
	private void changeFileOutput(ActionEvent event) {
		Node node = (Node) event.getSource() ;
		ValidatedTextField validatedTextField = (ValidatedTextField)
				node.getUserData();
		Utils.changeFileOutput(validatedTextField, validatedTextField.getLabel()
				.getText());
	}
	

}
