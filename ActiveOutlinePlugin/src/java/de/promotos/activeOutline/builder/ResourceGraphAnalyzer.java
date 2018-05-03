package de.promotos.activeOutline.builder;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class ResourceGraphAnalyzer implements ResourceAnalyzer {

	private static final String CONSOLE_NAME = "Active Outline";
	
	@Override
	public void analyze(IResource resource) {
		   MessageConsole myConsole = findConsole(CONSOLE_NAME);
		   MessageConsoleStream out = myConsole.newMessageStream();
		   out.println("Analyze: " + resource);
	}

	@Override
	public void analyze(IResourceDelta delta) {
		IResource resource = delta.getResource();
		
	   MessageConsole myConsole = findConsole(CONSOLE_NAME);
	   MessageConsoleStream out = myConsole.newMessageStream();
	   
		
		switch (delta.getKind()) {
		case IResourceDelta.ADDED:
			out.println("Analyze added: " + resource);
			break;
		case IResourceDelta.REMOVED:
			out.println("Analyze removed: " + resource);
			break;
		case IResourceDelta.CHANGED:
			out.println("Analyze changed: " + resource);
			break;
		}
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
