/**
 * 
 */
package application.controller.validator;

import java.io.File;
import java.util.ArrayList;

/**
 * @author fernando
 *
 */
public class Validator{
	
	public static boolean validate(ValidationEnum[] validators, String value, 
			ArrayList<String> errorMessages) {
		boolean isValid = true;
		boolean isValidatorValid;
		for (ValidationEnum validator : validators) {
			isValidatorValid = validateValidator(validator, value, 
					errorMessages);
			isValid = isValid && isValidatorValid;
		}
		return isValid;
	}

	private static boolean validateValidator(ValidationEnum validator, String
			value, ArrayList<String> errorMessages) {
		if (validator == ValidationEnum.INPUT_FILE) {
			File file = new File(value);
			if (file.exists()) {					
				return true;
			} 
			errorMessages.add("File doesn't exist: " + value);
			return false;
			
		} 
		else if (validator == ValidationEnum.OUTPUT_DIR) {
			boolean isValido = true;
			File file = new File(value);
			File dir = file.getParentFile();
			if (!dir.exists()) {					
				isValido = false;
				errorMessages.add("Directory doesn't exist: " + file.getParent());
			} 
			else if (!dir.canWrite()) {
				isValido = false;
				errorMessages.add("Missing write permissions for  "
						+ "directory: " + file.getParent());
			}
			
			return isValido;
			
		} 
		else if (validator == ValidationEnum.INT){
			try {
				int number = 0;
				number = Integer.parseInt(value);
				if ((number % 1) == 0) {
					return true;
				}
			} catch (NumberFormatException e) {
				
			}
			errorMessages.add(value + " is not an integer.");
			return false;
		}  
		else if (validator == ValidationEnum.POSITIVE_NUMBER) {
			try {
				double number = 0;
				number = Double.parseDouble(value);
				if (number >= 0) {
					return true;
				}
			} catch (NumberFormatException e) {
				
			}
			errorMessages.add(value + " is not a positive number.");
			return false;
		}
		else {
			try {
				throw new Exception("Unknown validator: " + validator);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	}
	
	public static boolean validate(ValidationEnum[] validators, String value, 
			StringBuilder errorsMessage, String errorsHeader) {
		ArrayList<String> errors = new ArrayList<String>();
		boolean isFieldValid = 
				Validator.validate(validators
						, value, errors);
		if (!isFieldValid) {
			addErrorsWithHeader(
					errorsHeader, errors, errorsMessage);
		}
		return isFieldValid;
	}
	
	private static void addErrorsWithHeader(String header
			, ArrayList<String> errors, StringBuilder errorsMessage) {
		errorsMessage.append(header + System.lineSeparator());
		for (String string : errors) {
			errorsMessage.append("\t" +  string + System.lineSeparator());
		}
	}

}
