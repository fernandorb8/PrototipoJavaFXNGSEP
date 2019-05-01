/**
 * 
 */
package ngsepfx.view.component;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ngsepfx.controller.validator.ValidationEnum;

/**
 * @author fernando
 *
 */
public class ValidatedTextField extends TextField {
	
	// Attributes.
	
	@FXML
	private ValidationEnum[] validationEnum;
	
	@FXML
	private Label label;
	
	// Methods.

	/**
	 * @return the validators
	 */
	public ValidationEnum[] getValidators() {
		return validationEnum;
	}

	/**
	 * @param validators the validators to set
	 */
	public void setValidators(ValidationEnum[] validators) {
		this.validationEnum = validators;
	}

	/**
	 * @return the label
	 */
	public Label getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(Label label) {
		this.label = label;
	}
	
}
