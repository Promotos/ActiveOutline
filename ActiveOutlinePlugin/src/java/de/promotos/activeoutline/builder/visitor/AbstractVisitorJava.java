package de.promotos.activeoutline.builder.visitor;

import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jdt.core.ICompilationUnit;

public abstract class AbstractVisitorJava extends AbstractVisitor {

    protected abstract void added(ICompilationUnit cu);

    protected abstract void changed(ICompilationUnit cu);

    protected abstract void removed(ICompilationUnit cu);

    public static final void meth(ICompilationUnit cu) {

    }

    @Override
    protected void added(final IResource resource) {
        final ICompilationUnit cu = getCompilationUnit(resource);
        if (cu != null) {
            added(cu);
        }
    }

    @Override
    protected void changed(final IResource resource) {
        final ICompilationUnit cu = getCompilationUnit(resource);
        if (cu != null) {
            changed(cu);
        }
    }

    @Override
    protected void removed(final IResource resource) {
        final ICompilationUnit cu = getCompilationUnit(resource);
        if (cu != null) {
            removed(cu);
        }
    }

    protected @Nullable ICompilationUnit getCompilationUnit(final IResource resource) {
        final Optional<IFile> javaFile = getJavaFile(resource);
        if (javaFile.isPresent()) {
            final @Nullable ICompilationUnit cu = getCompilationUnit(javaFile.get());
            if (cu != null) {
                return cu;
            }
        }
        return null;
    }

}
