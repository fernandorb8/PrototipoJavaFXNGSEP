/**
 * 
 */
package application.controller.fileexplorer;

import java.io.File;

import application.event.NGSEPAnalyzeFileEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 * @author fernando
 *
 */
public final class ContextMenuFactory {
	
	/**
	 * Create a ContextMenu for the given FileExplorerTreeCell.
	 * @param cell FileExplorerTreeCell
	 * @return ContextMenu for the cell.
	 */
	public static final ContextMenu getContextMenu( 
			FileExplorerTreeCell cell) {
    	FileTreeItem fileTreeItem = (FileTreeItem) cell.getTreeItem();
		File file = fileTreeItem.getFile();
	    ContextMenu contextMenu = new ContextMenu();
	    // TODO Build menu based on file type.
	    if(true) {
	    	addCountLines(contextMenu, cell);
	    	
	    }
	    return contextMenu;
	}
	
	/**
	 * Add a "Contar líneas" entry to the ContextMenu.
	 * @param contextMenu
	 * @param cell
	 */
	private static final void addCountLines(ContextMenu contextMenu, 
			FileExplorerTreeCell cell) {
			MenuItem countMenuItem = new MenuItem("Contar líneas");
		    countMenuItem.setOnAction((ActionEvent t) -> {        	
		    	t.consume();
		    	cell.fireEvent(
		        		new NGSEPAnalyzeFileEvent(
		        				"application.controller.CountFileLinesController"
		        				)
		        		);
		    });
		    contextMenu.getItems().add(countMenuItem);
	}

}
