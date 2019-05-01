package ngsepfx.controller.validator;

import java.util.ArrayList;

public class ValidationErrorUtils {
	
	public static final String toHierarchichalString(
			ArrayList<ValidationError> errors) {
		String errorsString = "";
		for (ValidationError validationError : errors) {
			errorsString += validationError.getSection() + System.lineSeparator();
			for (String error : validationError.getErrors()) {
				errorsString += "\t" + error + System.lineSeparator();
			}
		}
		return errorsString;
	}
	
}
