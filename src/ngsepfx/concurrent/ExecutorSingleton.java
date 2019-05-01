/**
 * 
 */
package ngsepfx.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ngsepfx.event.NGSEPExecuteTaksEvent;

/**
 * Singleton {@link ExecutorService} to execute an {@link NGSEPExecuteTaksEvent}'s
 * task.
 * @author fernando
 *
 */
public final class ExecutorSingleton {
	
	private static ExecutorService executor;
	
	/**
	 * Get a 10 pool sized {@link Executors#newFixedThreadPool(int)}.
	 * @return ExecutorService singleton.
	 */
	public static final ExecutorService getExecutor() {
		if (executor == null) {
			executor = Executors.newFixedThreadPool(10);
		}
		return executor;
	}
	
	/**
	 * Waits 5 seconds for all tasks to finish and then stops them all.
	 */
	public static final void stopExecutor() {
		if (executor != null) {
			try {
			    System.out.println("attempt to shutdown executor");
			    executor.shutdown();
			    executor.awaitTermination(5, TimeUnit.SECONDS);
			}
			catch (InterruptedException e) {
			    System.err.println("tasks interrupted");
			}
			finally {
			    if (!executor.isTerminated()) {
			        System.err.println("cancel non-finished tasks");
			    }
			    executor.shutdownNow();
			    System.out.println("shutdown finished");
			}
		}
	}

}
