package de.promotos.activeoutline.builder.visitor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.annotation.Nullable;

import de.promotos.activeoutline.lang.Assert;

public class DelegatingVisitor implements IResourceVisitor, IResourceDeltaVisitor {
	
	protected final IProgressMonitor monitor;

	private final List<Visitable> visitables = Assert.notNull(
			Collections.unmodifiableList( 
					Arrays.asList( 
					        new ConsolePrinterVisitor(),
					        new JavaPackageVisitor()
					        ) 
		 			)
			);
	
	public DelegatingVisitor(final IProgressMonitor monitor) {
		this.monitor = monitor;
	}
	
	@Override
	public boolean visit(final @Nullable IResource resource) {
	    if (resource != null) {
	        visitables.stream().forEach( visitor -> visitor.visit(resource) );
	        return true;
	    }
	    return false;
	}

	@Override
	public boolean visit(final @Nullable IResourceDelta resource) throws CoreException {
	    if (resource != null) {
    		visitables.stream().forEach( visitor -> visitor.visit(resource) );
    		return true;
	    }
	    return false;
	}
	
}
