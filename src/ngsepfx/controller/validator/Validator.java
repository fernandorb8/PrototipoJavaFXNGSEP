/**
 * 
 */
package ngsepfx.controller.validator;

import java.io.File;
import java.util.ArrayList;

import ngsepfx.view.component.ValidatedTextField;

/**
 * @author fernando
 *
 */
public class Validator{
	
	public static ValidationError validate(
			ValidationEnum[] validators, String value, 
			String section) {
		ValidationError validationError = null;
		if (needsValidation(validators, value)) {		
			String error = null;
			for (ValidationEnum validator : validators) {
				error = validateValidator(validator, value);
				if (error != null) {
					if (validationError == null) {
						validationError = new ValidationError(section);
						validationError.setSection(section);	
					}
					validationError.addError(error);
				}
			}
		}
		return validationError;
	}

	private static boolean needsValidation(ValidationEnum[] validators
			, String value) {
		boolean isMandatory = false;
		for (ValidationEnum validationEnum : validators) {
			if (validationEnum == ValidationEnum.MANDATORY) {
				isMandatory = true;
			}
		}
		if (isMandatory) {
			return true;
		} else {
			if (value.trim().isEmpty()) {
				return false;
			}
			return true;
		}
	}

	private static String validateValidator(ValidationEnum validator
			, String value) {
		if (validator == ValidationEnum.INPUT_FILE) {
			File file = new File(value);
			if (file.exists()) {					
				return null;
			} 
			return "File doesn't exist: " + value;
			
		} 
		else if (validator == ValidationEnum.OUTPUT_DIR) {
			File file = new File(value);
			File dir = file.getParentFile();
			if (dir == null) {					
				return "Directory doesn't exist";
			} 
			if (!dir.exists()) {					
				return "Directory doesn't exist: " + file.getParent();
			}
			else if (!dir.canWrite()) {
				return "Missing write permissions for  "
						+ "directory: " + file.getParent();
			}
			
			return null;
			
		} 
		else if (validator == ValidationEnum.INT){
			try {
				int number = 0;
				number = Integer.parseInt(value);
				if ((number % 1) == 0) {
					return null;
				}
			} catch (NumberFormatException e) {
				
			}
			return value + " is not an integer.";
		}  
		else if (validator == ValidationEnum.POSITIVE_NUMBER) {
			try {
				double number = 0;
				number = Double.parseDouble(value);
				if (number >= 0) {
					return null;
				}
			} catch (NumberFormatException e) {
				
			}
			return value + " is not a positive number.";
		}
		else if (validator == ValidationEnum.MANDATORY) {
			if (value.trim().isEmpty()) {
				return "Value is mandatory";
			}
			return null;
		}
		else {
			throw new RuntimeException("Unknown validator: " + validator);
		}
	}

	public static boolean defaultValidatedTextFieldValidation(
			ValidatedTextField validatedTextField,
			ArrayList<ValidationError> errorsArray) {
		ValidationError error = Validator.validate(
				validatedTextField.getValidators()
				, validatedTextField.getText()
				, validatedTextField.getLabel().getText());
		if (error != null) {
			validatedTextField.getStyleClass().add("error");
			errorsArray.add(error);
			return false;
		} else {
			validatedTextField.getStyleClass().remove("error");
			return true;
		}
	}

}
