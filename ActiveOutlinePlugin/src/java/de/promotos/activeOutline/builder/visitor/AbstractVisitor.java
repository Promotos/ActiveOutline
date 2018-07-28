package de.promotos.activeOutline.builder.visitor;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;

import de.promotos.activeOutline.lang.Assert;

public abstract class AbstractVisitor implements Visitable {

    protected abstract void added(IResource resource);
    protected abstract void changed(IResource resource);
    protected abstract void removed(IResource resource);
    
    @Override
    public void visit(final IResource resource) {
        changed(resource);
    }

    @Override
    public void visit(IResourceDelta delta) {
        final IResource resource = Assert.notNull(delta.getResource());
        switch (delta.getKind()) {
        case IResourceDelta.ADDED:
            added(resource);
            break;
        case IResourceDelta.CHANGED:
            changed(resource);
            break;
        case IResourceDelta.REMOVED:
            removed(resource);
            break;
        }
    }

}
