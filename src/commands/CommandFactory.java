package commands;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ResourceBundle;

import slogo_team07.Updatable;

public class CommandFactory {

	private static final String COMMANDS = "resources.commands.Command";

	ResourceBundle myResources;
	List<Updatable> myUpdatables;

	public CommandFactory(List<Updatable> updatables) {
		myUpdatables = updatables;
		myResources = ResourceBundle.getBundle(COMMANDS);
	}

	public Commandable createCommand(String command) {
		try {
			Class<?> clazz = Class.forName(myResources.getString(command) + "Command");
			Constructor<?> ctor = clazz.getConstructor();
			return (Commandable) ctor.newInstance();
		} catch (Exception e) {
			return new ExceptionCommand();
		}
	}	
}
