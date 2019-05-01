/**
 * 
 */
package ngsepfx.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import ngsepfx.controller.fileexplorer.FileExplorerTreeCell;
import ngsepfx.controller.fileexplorer.FileTreeItem;
import ngsepfx.event.NGSEPEvent;

/**
 * Test controller demonstrating the use on {@link AnalysisAreaController}.
 * @author fernando
 */
public class CountFileLinesController extends AnalysisAreaController {
	
	// Attributes.
	
	@FXML
	private Text fileNameText;
	
	@FXML
	private Text numberLinesText;
	
	// AnalysisAreaController methods.

	@Override
	public String getCSSExternalForm() {
		return getClass()
				.getResource("/ngsepfx/view/countfilelinesanalysis.css")
				.toExternalForm();
	}

	@Override
	public URL getFXMLResource() {
		return getClass()
				.getResource("/ngsepfx/view/CountFileLinesAnalysis.fxml");
	}

	/* (non-Javadoc)
	 * @see ngsepfx.controller.AnalysisAreaController#handleNGSEPEvent(ngsepfx.event.NGSEPEvent)
	 */
	@Override
	public void handleNGSEPEvent(NGSEPEvent event) {
		try {
			FileExplorerTreeCell cell = (FileExplorerTreeCell) 
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

}
