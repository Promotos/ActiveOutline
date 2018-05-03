package de.promotos.activeOutline.builder;

import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

class DeltaVisitor implements IResourceDeltaVisitor {
	
	@Override
	public boolean visit(IResourceDelta delta) throws CoreException {
		new ResourceGraphAnalyzer().analyze(delta);
		return true;
	}
}
