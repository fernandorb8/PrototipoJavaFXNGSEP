/**
 * 
 */
package ngsepfx.controller.validator;

import java.util.ArrayList;

/**
 * @author fernando
 *
 */
public class ValidationError {
	
	// Attributes.
	
	private String section;
	
	private ArrayList<String> errors;
	
	//Consructors
	
	public ValidationError(String section) {
		this.section = section;
		this.errors = new ArrayList<>();
	}
	
	// Getters and setters.

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}	
	
	/**
	 * @return the errors
	 */
	public ArrayList<String> getErrors() {
		return errors;
	}
	
	// Methods

	public void addError(String error) {
		this.getErrors().add(error);
	}
}
