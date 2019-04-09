/**
 * 
 */
package application.concurrent;

import application.controller.TestAnalysisController;
import javafx.concurrent.Task;
import ngsep.main.ProgressNotifier;

/**
 * Class to create a {@link Task} that updates the progress of the {@link Task}
 * using {@link ProgressNotifier#keepRunning(int)} and determines if the 
 * {@link Task} is cancelled using {@link Task#isCancelled()}. 
 * The developer must not call {@link Task}'s updateProgress(int,int).
 * @author fernando
 *
 */
public abstract class NGSEPTask<V> extends Task<V> implements ProgressNotifier{
		
	// ProgressNotifier
	
	@Override
	public boolean keepRunning(int progress) {
		super.updateProgress(progress, 100);
		return !super.isCancelled();
	}

}
