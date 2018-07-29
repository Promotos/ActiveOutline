package de.promotos.activeoutline.builder.visitor;

import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;

import de.promotos.activeoutline.lang.Assert;
import de.promotos.activeoutline.lang.OptionalNullSafe;

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
    
    protected @Nullable ICompilationUnit getCompilationUnit(final IFile file) {
        return JavaCore.createCompilationUnitFrom(file);
    }

    protected Optional<IFile> getJavaFile(final IResource resource) {
        if (resource instanceof IFile && resource.getName().endsWith(".java")) {
            return OptionalNullSafe.of((IFile) resource);
        }
        return OptionalNullSafe.empty();
    }

}
