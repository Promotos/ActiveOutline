package de.promotos.activeOutline.builder;

import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class ResourceGraphAnalyzer implements ResourceAnalyzer {

	private static final String CONSOLE_NAME = "Active Outline";

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
			getOut().println("Added CU: " + cu.orElseThrow());
		}
	}

	private void changed(final IResource resource) {
		Optional<IFile> javaFile = getJavaIFile(resource);
		if (javaFile.isPresent()) {

			Optional<ICompilationUnit> cu = Optional.ofNullable(JavaCore.createCompilationUnitFrom(javaFile.get()));
			getOut().println("Changed CU: " + cu.orElseThrow());
		}
	}

	private void removed(final IResource resource) {
		Optional<IFile> javaFile = getJavaIFile(resource);
		if (javaFile.isPresent()) {

			Optional<ICompilationUnit> cu = Optional.ofNullable(JavaCore.createCompilationUnitFrom(javaFile.get()));
			getOut().println("Removed CU: " + cu.orElseThrow());
		}
	}

	private Optional<IFile> getJavaIFile(final IResource resource) {
		if (resource instanceof IFile && resource.getName().endsWith(".java")) {
			return Optional.of((IFile) resource);
		}
		return Optional.empty();
	}

	private MessageConsoleStream getOut() {
		MessageConsole myConsole = findConsole(CONSOLE_NAME);
		return myConsole.newMessageStream();
	}

	private MessageConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName()))
				return (MessageConsole) existing[i];
		// no console found, so create a new one
		MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[] { myConsole });
		return myConsole;
	}

}
