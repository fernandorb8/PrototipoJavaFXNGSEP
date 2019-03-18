/*
    Debigulator - A batch compression utility
Copyright (C) 2003-2018 Hugues Johnson

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
/*
 * Modified for this specific software.
 */

package application.controller;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FileTreeItem extends TreeItem<String> {
	public static Image folderCollapseImage = new Image(
			ClassLoader.getSystemResourceAsStream("com/huguesjohnson/debigulatorfx/res/folder.png"));
	public static Image folderExpandImage = new Image(
			ClassLoader.getSystemResourceAsStream("com/huguesjohnson/debigulatorfx/res/folder-open.png"));
	public static Image fileImage = new Image(
			ClassLoader.getSystemResourceAsStream("com/huguesjohnson/debigulatorfx/res/text-x-generic.png"));
	// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TreeItem.html
	// We cache whether the File is a leaf or not. A File is a leaf if
	// it is not a directory and does not have any files contained within
	// it. We cache this as isLeaf() is called often, and doing the
	// actual check on File is expensive.
	private boolean isLeaf;

	// We do the children and leaf testing only once, and then set these
	// booleans to false so that we do not check again during this
	// run. A more complete implementation may need to handle more
	// dynamic file system situations (such as where a folder has files
	// added after the TreeView is shown). Again, this is left as an
	// exercise for the reader.
	private boolean isFirstTimeChildren = true;
	private boolean isFirstTimeLeaf = true;

	private final File file;
	private final String absolutePath;
	private final boolean isDirectory;

	public FileTreeItem(File file) {
		super(file.getAbsolutePath());
		this.file = file;
		this.absolutePath = file.getAbsolutePath();
		this.isDirectory = file.isDirectory();
		if (this.isDirectory) {
			this.setGraphic(new ImageView(folderCollapseImage));
			// add event handlers
			this.addEventHandler(TreeItem.branchCollapsedEvent(), new EventHandler() {
				@Override
				public void handle(Event e) {
					FileTreeItem source = (FileTreeItem) e.getSource();
					if (!source.isExpanded()) {
						ImageView iv = (ImageView) source.getGraphic();
						iv.setImage(folderCollapseImage);
					}
				}
			});
			this.addEventHandler(TreeItem.branchExpandedEvent(), new EventHandler() {
				@Override
				public void handle(Event e) {
					FileTreeItem source = (FileTreeItem) e.getSource();
					if (source.isExpanded()) {
						ImageView iv = (ImageView) source.getGraphic();
						iv.setImage(folderExpandImage);
					}
				}
			});
		} else {
			this.setGraphic(new ImageView(fileImage));
		}
		// set the value (which is what is displayed in the tree)
		String fullPath = file.getAbsolutePath();
		if (!fullPath.endsWith(File.separator)) {
			String value = file.toString();
			int indexOf = value.lastIndexOf(File.separator);
			if (indexOf > 0) {
				this.setValue(value.substring(indexOf + 1));
			} else {
				this.setValue(value);
			}
		}
	}

	public File getFile() {
		return (this.file);
	}

	public String getAbsolutePath() {
		return (this.absolutePath);
	}

	public boolean isDirectory() {
		return (this.isDirectory);
	}

	@Override
	public ObservableList<TreeItem<String>> getChildren() {
		if (isFirstTimeChildren) {
			isFirstTimeChildren = false;

			// First getChildren() call, so we actually go off and
			// determine the children of the File contained in this TreeItem.
			super.getChildren().setAll(buildChildren(this));
		}
		return super.getChildren();
	}

	@Override
	public boolean isLeaf() {
		if (isFirstTimeLeaf) {
			isFirstTimeLeaf = false;
			isLeaf = this.file.isFile();
		}

		return isLeaf;
	}

	private ObservableList<FileTreeItem> buildChildren(FileTreeItem treeItem) {
		File f = treeItem.getFile();
		if ((f != null) && (f.isDirectory())) {
			File[] files = f.listFiles();
			if (files != null) {
				ObservableList<FileTreeItem> children = FXCollections.observableArrayList();
				for (File childFile : files) {
					children.add(new FileTreeItem(childFile));
				}
				return (children);
			}
		}
		return FXCollections.emptyObservableList();
	}
}
