package de.promotos.activeoutline.lang;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class Assert {

    private Assert() {}
    
	public static final <T> @NonNull T notNull(final @Nullable T var) {
		if (var == null) {
			throw new IllegalStateException("Expect " + var + " not to be null.");
		}
		return var;
	}
	
}
