/**
 * 
 */
package ngsepfx.controller;

import java.io.File;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import ngsepfx.view.component.ValidatedTextField;

/**
 * @author fernando
 *
 */
public class ControllerUtils {
	
	public static void changeFileInput(ValidatedTextField validatedTextField
			, String title) {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(System.getProperty("user.home")));
		chooser.setTitle(title);
		File selectedFile = chooser.showOpenDialog(null);
		if (selectedFile != null) {
			validatedTextField.setText(selectedFile.getAbsolutePath());
		}
	}
	
	public static void changeFileOutput(ValidatedTextField validatedTextField,
			String fileName, String suffix) {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setInitialDirectory(new File(System.getProperty("user.home")));
		chooser.setTitle("Select output directory");
		File selectedDirectory = chooser.showDialog(null);
		if (selectedDirectory != null) {
			String newFileName = selectedDirectory.getAbsolutePath() 
					+ File.separator + fileName + suffix;
			validatedTextField.setText(newFileName);
		}
	}

	public static void showDefaultErrorDialog(String errorsMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		
		TextArea textArea = new TextArea(errorsMessage);
		textArea.setEditable(false);
		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);
		
		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(textArea, 0, 0);

		alert.setTitle("Validation Error");
		alert.setHeaderText("Errors in one or more fields");
		alert.setContentText("Errors in one or more fields");
		alert.initModality(Modality.NONE);
		alert.getDialogPane().setExpandableContent(expContent);		
		alert.getDialogPane().expandedProperty().set(true);

		alert.show();
		
	}

}
