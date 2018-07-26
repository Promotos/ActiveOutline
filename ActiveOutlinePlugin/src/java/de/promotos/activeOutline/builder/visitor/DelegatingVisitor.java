package de.promotos.activeOutline.builder.visitor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class DelegatingVisitor implements IResourceVisitor, IResourceDeltaVisitor {
	
	protected final IProgressMonitor monitor;

	private final List<Visitable> visitables = Collections.unmodifiableList( 
		Arrays.asList( new ConsolePrinterVisitor() ) 
		);
	
	public DelegatingVisitor(final IProgressMonitor monitor) {
		this.monitor = monitor;
	}
	
	@Override
	public boolean visit(final IResource resource) {
		visitables.stream().forEach( visitor -> visitor.visit(resource) );
		return true;
	}

	@Override
	public boolean visit(final IResourceDelta resource) throws CoreException {
		visitables.stream().forEach( visitor -> visitor.visit(resource) );
		return true;
	}
}
