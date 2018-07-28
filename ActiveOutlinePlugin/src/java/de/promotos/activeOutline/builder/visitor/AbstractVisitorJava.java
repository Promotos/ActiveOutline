package de.promotos.activeOutline.builder.visitor;

import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;

import de.promotos.activeOutline.lang.OptionalNullSafe;

public abstract class AbstractVisitorJava extends AbstractVisitor {

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
