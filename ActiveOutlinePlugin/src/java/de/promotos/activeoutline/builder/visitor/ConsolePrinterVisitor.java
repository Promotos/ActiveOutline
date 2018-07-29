package de.promotos.activeoutline.builder.visitor;

import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jdt.core.ICompilationUnit;

import de.promotos.activeoutline.console.ActiveOutlineConsole;

public class ConsolePrinterVisitor extends AbstractVisitor {

    protected void added(final IResource resource) {
		print("Added", resource);
	}

    protected void changed(final IResource resource) {
		print("Changed", resource);
	}

    protected void removed(final IResource resource) {
		print("Removed", resource);
	}

	private void print(final String action, final IResource resource) {
		final Optional<IFile> javaFile = getJavaFile(resource);
		if (javaFile.isPresent()) {
			final @Nullable ICompilationUnit cu = getCompilationUnit(javaFile.get());
			if (cu != null) {
			    ActiveOutlineConsole.out().println( String.format("%s CU: %s", action, cu) );
			}
		}
	}

}
