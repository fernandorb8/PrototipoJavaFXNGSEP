/**
 * 
 */
package application.controller.fileexplorer;

import java.io.File;
import java.util.concurrent.TimeUnit;

import application.concurrent.NGSEPTask;
import application.controller.MainController;
import application.event.NGSEPAnalyzeFileEvent;
import application.event.NGSEPExecuteTaksEvent;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 * Factory to create {@link ContextMenu} for the {@link FileExplorerTreeCell}.
 * @author fernando
 *
 */
public final class ContextMenuFactory {
	
	/**
	 * Create a {@link ContextMenu} for the given {@link FileExplorerTreeCell}.
	 * @param cell {@link FileExplorerTreeCell}
	 * @return {@link ContextMenu} for the {@link FileExplorerTreeCell}.
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
	 * Add a "Contar líneas" entry to the {@link ContextMenu}.
	 * @param contextMenu {@link ContextMenu} to be modified.
	 * @param cell {@link FileExplorerTreeCell} to fire 
	 * {@link NGSEPAnalyzeFileEvent} to the {@link MainController}.
	 */
	private static final void addCountLines(ContextMenu contextMenu, 
			FileExplorerTreeCell cell) {
		MenuItem countMenuItem = new MenuItem("Contar líneas");
		FileTreeItem fileTreeItem = (FileTreeItem) cell.getTreeItem();
	    countMenuItem.setOnAction((ActionEvent t) -> {        	
	    	t.consume();
	    	cell.fireEvent(
	        		new NGSEPAnalyzeFileEvent(
	        				"application.controller.CountFileLinesController",
	        				fileTreeItem.getFile())
	        		);
	    });
	    contextMenu.getItems().add(countMenuItem);
	}
	
	/**
	 * Test analysis with a {@link Button}.
	 * @param contextMenu {@link ContextMenu} to be modified.
	 * @param cell {@link FileExplorerTreeCell} to fire 
	 * {@link NGSEPAnalyzeFileEvent} to the {@link MainController}.
	 */
	private static final void addTest(ContextMenu contextMenu, 
			FileExplorerTreeCell cell) {
		MenuItem countMenuItem = new MenuItem("Test analysis");
	    countMenuItem.setOnAction((ActionEvent t) -> {        	
	    	t.consume();
	    	FileTreeItem fileTreeItem = (FileTreeItem) cell.getTreeItem();
	    	cell.fireEvent(
	        		new NGSEPAnalyzeFileEvent(
	        				"application.controller.TestAnalysisController",
	        				fileTreeItem.getFile())
	        		);
	    });
	    contextMenu.getItems().add(countMenuItem);
	}
	
	/**
	 * Test {@link Task} for {@link NGSEPExecuteTaksEvent}.
	 * @param contextMenu {@link ContextMenu} to be modified.
	 * @param cell {@link FileExplorerTreeCell} to fire 
	 * {@link NGSEPExecuteTaksEvent} to the {@link MainController}.
	 */
	private static final void addTest2(ContextMenu contextMenu, 
			FileExplorerTreeCell cell) {
		MenuItem countMenuItem = new MenuItem("Test execution");
	    countMenuItem.setOnAction((ActionEvent t) -> {        	
	    	t.consume();
	    	cell.fireEvent(
	        		new NGSEPExecuteTaksEvent(new NGSEPTask<Void>() {
	    	    		@Override 
	    	    		public Void call() {
	    	    			try {
	    	    				String threadName = Thread.currentThread().getName();
	    	    				System.out.println(threadName + " starting task");
	    	    				TimeUnit.SECONDS.sleep(10);
	    	    				System.out.println(threadName + " ending task");
	    	    			} catch (InterruptedException e) {
	    	    				// TODO Auto-generated catch block
	    	    				e.printStackTrace();
	    	    			}
	    	    			return null;
	    	    		}
	        		})
	        		);
	    });
	    contextMenu.getItems().add(countMenuItem);
	}

}
