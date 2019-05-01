/**
 * 
 */
package ngsepfx.concurrent;

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
	
	// Attributes.
	
	private int maxProgress = 100;
		
	// ProgressNotifier.
	
	@Override
	public boolean keepRunning(int progress) {
		super.updateProgress(progress, maxProgress);
		return !super.isCancelled();
	}
	
	// Getters and setters.

	/**
	 * @return the maxProgress
	 */
	public int getMaxProgress() {
		return maxProgress;
	}

	/**
	 * @param maxProgress the maxProgress to set
	 */
	public void setMaxProgress(int maxProgress) {
		this.maxProgress = maxProgress;
	}

}
