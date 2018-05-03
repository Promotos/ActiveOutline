package de.promotos.activeOutline.builder;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;

public interface ResourceAnalyzer {

	void analyze(IResource res);
	void analyze(IResourceDelta delta);
	
}
