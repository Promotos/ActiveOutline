package de.promotos.activeOutline;

import org.eclipse.core.runtime.Plugin;

public class ActiveOutlinePlugin extends Plugin {

	/**
	 * Singleton instance
	 */
	private static ActiveOutlinePlugin INSTANCE;
	
	public ActiveOutlinePlugin() {
		INSTANCE = this;
	}
	
	/**
	 * Get the singleton instance.
	 * @return The instance
	 */
	public static final ActiveOutlinePlugin instance() {
		return INSTANCE;
	}
	
}
