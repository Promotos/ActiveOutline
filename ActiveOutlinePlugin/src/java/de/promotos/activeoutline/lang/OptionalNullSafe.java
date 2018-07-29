package de.promotos.activeoutline.lang;

import java.util.Optional;

/**
 * Annotation null safe version of {@code java.util.Optional}.
 *
 */
public class OptionalNullSafe {

    /**
     * @see java.util.Optional#empty();
     */
    public static<T> Optional<T> empty() {
        return Assert.notNull(Optional.empty());
    }
    
    /**
     * @see java.util.Optional#of(Object);
     */
    public static <T> Optional<T> of(T value) {
        return Assert.notNull(Optional.of(value));
    }
}
