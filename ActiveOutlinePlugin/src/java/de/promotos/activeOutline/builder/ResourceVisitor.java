package de.promotos.activeOutline.builder;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;

class ResourceVisitor implements IResourceVisitor {
	public boolean visit(IResource resource) {
		new ResourceGraphAnalyzer().analyze(resource);
		return true;
	}
}
