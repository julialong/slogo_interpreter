package commands;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

public class CommandFactory {

	private static final String COMMANDS = "Commands";

	ResourceBundle myResources;

	public CommandFactory() {
		myResources = ResourceBundle.getBundle(COMMANDS);
	}

	public Commandable createCommand(String command) {
		try {
			Class<?> clazz = Class.forName(myResources.getString(command));
			Constructor<?> ctor = clazz.getConstructor();
			return (Commandable) ctor.newInstance();
		} catch (Exception e) {
			return new NullCommand();
		}
	}	
}
