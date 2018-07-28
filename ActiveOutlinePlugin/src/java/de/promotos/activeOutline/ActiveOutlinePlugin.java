package de.promotos.activeOutline;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.jdt.annotation.Nullable;

import de.promotos.activeOutline.lang.Assert;

public class ActiveOutlinePlugin extends Plugin {

	/**
	 * Singleton instance
	 */
	private static @Nullable ActiveOutlinePlugin INSTANCE;
	
	public ActiveOutlinePlugin() {
		INSTANCE = this;
	}
	
	/**
	 * Get the singleton instance.
	 * @return The instance
	 */
	public static final ActiveOutlinePlugin instance() {
		return Assert.notNull(INSTANCE);
	}
	
}
