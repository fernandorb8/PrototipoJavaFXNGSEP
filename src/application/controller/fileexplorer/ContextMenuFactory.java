/**
 * 
 */
package application.controller.fileexplorer;

import java.io.File;
import java.util.concurrent.TimeUnit;

import application.event.NGSEPAnalyzeFileEvent;
import application.event.NGSEPExecuteTaksEvent;
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
	    	addTest(contextMenu, cell);
	    	addTest2(contextMenu, cell);
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
	
	private static final void addTest(ContextMenu contextMenu, 
			FileExplorerTreeCell cell) {
		MenuItem countMenuItem = new MenuItem("Test analysis");
	    countMenuItem.setOnAction((ActionEvent t) -> {        	
	    	t.consume();
	    	cell.fireEvent(
	        		new NGSEPAnalyzeFileEvent(
	        				"application.controller.TestAnalysisController"
	        				)
	        		);
	    });
	    contextMenu.getItems().add(countMenuItem);
	}
	
	private static final void addTest2(ContextMenu contextMenu, 
			FileExplorerTreeCell cell) {
		MenuItem countMenuItem = new MenuItem("Test execution");
	    countMenuItem.setOnAction((ActionEvent t) -> {        	
	    	t.consume();
	    	cell.fireEvent(
	        		new NGSEPExecuteTaksEvent(() -> {
	        			try {
	        				String threadName = Thread.currentThread().getName();
	        				System.out.println(threadName + " starting task");
	        				TimeUnit.SECONDS.sleep(10);
	        				System.out.println(threadName + " ending task");
	        			} catch (InterruptedException e) {
	        				// TODO Auto-generated catch block
	        				e.printStackTrace();
	        			}
	        		})
	        		);
	    });
	    contextMenu.getItems().add(countMenuItem);
	}

}
