package de.promotos.activeOutline.builder.visitor;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;

public interface Visitable {

	void visit(IResource res);
	void visit(IResourceDelta delta);
	
}
