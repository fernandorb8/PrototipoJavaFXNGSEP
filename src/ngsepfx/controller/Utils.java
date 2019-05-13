/**
 * 
 */
package ngsepfx.controller;

import java.io.File;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import ngsepfx.view.component.ValidatedTextField;

/**
 * @author fernando
 *
 */
public class Utils {
	
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
			String suffix) {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setInitialDirectory(new File(System.getProperty("user.home")));
		chooser.setTitle("Select output directory");
		File selectedDirectory = chooser.showDialog(null);
		if (selectedDirectory != null) {
			File file = new File(validatedTextField.getText());
			int extensionIndex = file.getName().lastIndexOf('.');
			String extensionlessName = file.getName().substring(0, extensionIndex);
			String newFileName = selectedDirectory.getAbsolutePath() 
					+ File.separator + extensionlessName + suffix + ".txt";
			validatedTextField.setText(newFileName);
		}
	}

}
