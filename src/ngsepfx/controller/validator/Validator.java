/**
 * 
 */
package ngsepfx.controller.validator;

import java.io.File;

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
				if(file.canRead()) {
					return null;
				}
				return "Cannot read file: " + value;
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
				Integer.parseInt(value);
				return null;
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
		else if (validator == ValidationEnum.PERCENTAGE) {
			try {
				double number = 0;
				number = Double.parseDouble(value);
				if (number >= 0 && number <= 1) {
					return null;
				}
			} catch (NumberFormatException e) {
				
			}
			return value + " is not a percentage in range [0,1]";
		}
		else {
			throw new RuntimeException("Unknown validator: " + validator);
		}
	}

}
