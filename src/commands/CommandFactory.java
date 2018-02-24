package commands;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;

import slogo_team07.Updatable;

public class CommandFactory {

	private static final String COMMANDS = "resources.commands.Command";

	ResourceBundle myResources;
	Map<String, Updatable> myUpdatables;

	public CommandFactory(Map<String, Updatable> updatables) {
		myUpdatables = updatables;
		myResources = ResourceBundle.getBundle(COMMANDS);
	}

	public Commandable createCommand(String command) {
		return createCommmand(command, "0");
	}
	
	public Commandable createCommmand(String command, String id) {
		try {
			Class<?> clazz = Class.forName(myResources.getString(command) + "Command");
			System.out.println(clazz.getSuperclass());
			Constructor<?> ctor = clazz.getConstructor();
			return (Commandable) ctor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return new ExceptionCommand();
		}
	}
}
