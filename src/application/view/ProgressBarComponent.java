package application.view;

import java.io.IOException;

import application.event.NGSEPAnalyzeFileEvent;
import application.event.NGSEPExecuteTaksEvent;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ngsep.main.ProgressNotifier;

/**
 * A custom component with a {@link ProgressBar} for the application.
 * @author fernando
 *
 */
public class ProgressBarComponent extends VBox {
	
	// Attributes.
	
    @FXML 
    private Text taskNameText;
    
    @FXML 
    private Text fileNameText;
    
    @FXML
    private ProgressBar taskProgressBar;
    
    public boolean shouldKeepRunning;
    
    // Constructor.

    /**
     * Loads the node graph of this custom component.
     */
    public ProgressBarComponent() {
    	shouldKeepRunning = true;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
        		.getResource("/application/view/ProgressBarComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    // FXML Life cycle methods.
	
 	/**
 	 * Set progress to 0.
 	 */
 	@FXML
 	private void initialize() {
 		setProgress(0); 		
 	}
    
    // Methods.
    
    	// taskNameText

    public String getTaskName() {
        return taskNameTextProperty().get();
    }

    public void setTaskName(String value) {
    	taskNameTextProperty().set(value);
    }

    public StringProperty taskNameTextProperty() {
        return taskNameText.textProperty();
    }
    
    	// fileNameText
    
    public String getFileName() {
        return taskFileNameTextProperty().get();
    }

    public void setFileName(String value) {
    	taskFileNameTextProperty().set(value);
    }

    public StringProperty taskFileNameTextProperty() {
        return fileNameText.textProperty();
    }
    
    	// taskProgressBar
    
    public double getProgress() {
        return taskProgressBarDoubleProperty().get();
    }

    public void setProgress(double value) {
    	taskProgressBarDoubleProperty().set(value);
    }

    public DoubleProperty taskProgressBarDoubleProperty() {
        return taskProgressBar.progressProperty();
    }
    
    	// Button

    /**
     * Set the {@link ProgressBarComponent#shouldKeepRunning} to false in order
     * to indicate the NGSEP analysis to stop.
     */
    @FXML
    protected void cancelProcess() {
    	shouldKeepRunning = false;
    }

}
