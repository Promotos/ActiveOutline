package de.promotos.activeOutline.builder.visitor;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;

public interface Visitable {

	boolean visit(IResource res);
	boolean visit(IResourceDelta delta);
	
}
