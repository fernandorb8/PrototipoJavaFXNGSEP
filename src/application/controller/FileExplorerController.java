package application.controller;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FileExplorerController {

	@FXML
	private TreeView<String> treeviewFileBrowse;

	@FXML
	void initialize() {
		// populate file browser
		this.treeviewFileBrowse.setCellFactory((TreeView<String> p) -> new TextFieldTreeCellImpl());
		rootDirectoriesRoot();
	}
	
	/**
	 * Set root directories as children of root node.
	 */
	private void rootDirectoriesRoot() {
		String hostName = null;
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException x) {
			System.out.println(x);
		} // TODO error dialog
		assert hostName != null : "Unable to get local host name.";
		TreeItem<String> rootNode = new TreeItem<String>(hostName, new ImageView(
				new Image(ClassLoader.getSystemResourceAsStream("com/huguesjohnson/debigulatorfx/res/computer.png"))));
		Iterable<Path> rootDirectories = FileSystems.getDefault().getRootDirectories();
		for (Path name : rootDirectories) {
			FileTreeItem treeNode = new FileTreeItem(new File(name.toString()));
			rootNode.getChildren().add(treeNode);
		}
		rootNode.setExpanded(true);
		this.treeviewFileBrowse.setRoot(rootNode);
	}
	
	@FXML
	private void changeDir(ActionEvent ae) {		
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Seleccionar carpeta");
		File selectedDirectory = chooser.showDialog(null);
		if (selectedDirectory != null) {
			FileTreeItem rootNode = new FileTreeItem(selectedDirectory);
			rootNode.setExpanded(true);
			this.treeviewFileBrowse.setRoot(rootNode);
		}
	}
	

}
