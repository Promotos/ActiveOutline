package de.promotos.activeOutline.console;

import java.util.Arrays;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import de.promotos.activeOutline.lang.Assert;

public class ActiveOutlineConsole {

	private static final String CONSOLE_NAME = "Active Outline";
	
	public static MessageConsoleStream out() {
		return Assert.notNull(findConsole(CONSOLE_NAME).newMessageStream());
	}

	private static MessageConsole findConsole(String name) {
		final ConsolePlugin consolePlugin = ConsolePlugin.getDefault();
		final IConsoleManager consoleManager = consolePlugin.getConsoleManager();
		
		return Assert.notNull(Arrays.stream(consoleManager.getConsoles())
			.filter(console -> console instanceof MessageConsole)
			.map(console -> (MessageConsole) console)
			.filter(console -> CONSOLE_NAME.equals(console.getName()))
			.findFirst()
			.orElseGet( () -> createNewConsole(consoleManager)));
	}
	
	
	private final static MessageConsole createNewConsole(final IConsoleManager consoleManager) {
		final MessageConsole newConsole = new MessageConsole(CONSOLE_NAME, null);
		consoleManager.addConsoles(new IConsole[] { newConsole });
		return newConsole;
	}
	
}
