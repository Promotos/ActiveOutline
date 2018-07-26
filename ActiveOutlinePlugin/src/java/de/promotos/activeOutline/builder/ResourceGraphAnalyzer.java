package de.promotos.activeOutline.builder;

import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;

import de.promotos.activeOutline.console.ActiveOutlineConsole;

public class ResourceGraphAnalyzer implements ResourceAnalyzer {

	@Override
	public void analyze(final IResource resource) {
		changed(resource);
	}

	@Override
	public void analyze(IResourceDelta delta) {
		final IResource resource = delta.getResource();

		switch (delta.getKind()) {
		case IResourceDelta.ADDED:
			added(resource);
			break;
		case IResourceDelta.CHANGED:
			changed(resource);
			break;
		case IResourceDelta.REMOVED:
			removed(resource);
			;
			break;
		}
	}

	private void added(final IResource resource) {
		Optional<IFile> javaFile = getJavaIFile(resource);
		if (javaFile.isPresent()) {

			Optional<ICompilationUnit> cu = Optional.ofNullable(JavaCore.createCompilationUnitFrom(javaFile.get()));
			ActiveOutlineConsole.out().println("Added CU: " + cu.orElseThrow());
		}
	}

	private void changed(final IResource resource) {
		Optional<IFile> javaFile = getJavaIFile(resource);
		if (javaFile.isPresent()) {

			Optional<ICompilationUnit> cu = Optional.ofNullable(JavaCore.createCompilationUnitFrom(javaFile.get()));
			ActiveOutlineConsole.out().println("Changed CU: " + cu.orElseThrow());
		}
	}

	private void removed(final IResource resource) {
		Optional<IFile> javaFile = getJavaIFile(resource);
		if (javaFile.isPresent()) {

			Optional<ICompilationUnit> cu = Optional.ofNullable(JavaCore.createCompilationUnitFrom(javaFile.get()));
			ActiveOutlineConsole.out().println("Removed CU: " + cu.orElseThrow());
		}
	}

	private Optional<IFile> getJavaIFile(final IResource resource) {
		if (resource instanceof IFile && resource.getName().endsWith(".java")) {
			return Optional.of((IFile) resource);
		}
		return Optional.empty();
	}

}
