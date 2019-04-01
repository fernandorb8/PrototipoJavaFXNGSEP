/**
 * 
 */
package application.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import application.controller.fileexplorer.FileTreeItem;
import application.controller.fileexplorer.TextFieldTreeCellImpl;
import application.event.NGSEPEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author fernando
 *
 */
public class CountFileLinesController implements IAnalysisAreaController {
	
	// Attributes.
	
	@FXML
	private Text fileNameText;
	
	@FXML
	private Text numberLinesText;
	
	@FXML
	private VBox root;
	
	private Scene scene;
	
	private String cssExternalForm;
	
	// IAnalysisAreaController methods.

	@Override
	public void initializeController(Scene scene) {
		try {
			this.scene = scene;
			cssExternalForm = getClass()
					.getResource("/application/view/countfilelinesanalysis.css")
					.toExternalForm();
			scene.getStylesheets().add(cssExternalForm);
			URL fxmlLocation = getClass()
					.getResource("/application/view/CountFileLinesAnalysis.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
			fxmlLoader.setController(this);
			this.root = (VBox) fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see application.controller.IAnalysisAreaController#getRootNode()
	 */
	@Override
	public Node getRootNode() {
		return root;
	}

	/* (non-Javadoc)
	 * @see application.controller.IAnalysisAreaController#handleNGSEPEvent(application.event.NGSEPEvent)
	 */
	@Override
	public void handleNGSEPEvent(NGSEPEvent event) {
		try {
			TextFieldTreeCellImpl cell = (TextFieldTreeCellImpl) 
					event.getTarget();
			FileTreeItem fileTreeItem = (FileTreeItem) cell.getTreeItem();
			File file = fileTreeItem.getFile();
			fileNameText.setText(file.getName());
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// https://stackoverflow.com/questions/1277880/how-can-i-get-the-count-of-line-in-a-file-in-an-efficient-way
			int lines = 0;
			while (bufferedReader.readLine() != null) lines++;
			bufferedReader.close();
			numberLinesText.setText(lines + "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void prepareForReplacement() {
		scene.getStylesheets().remove(cssExternalForm);
	}

}
