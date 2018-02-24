package commands;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import slogo_team07.Updatable;

public class CommandFactory {

	private static final String COMMANDS = "resources.commands.Command";
	private static final String LANGUAGE_BASE = "resources.languages.";
	private static final String DEFAULT = "English";
	
	ResourceBundle myCommands = ResourceBundle.getBundle(COMMANDS);
	Map<String, String> myLanguages = new HashMap<>();
	Map<String, Updatable> myUpdatables;

	public CommandFactory(Map<String, Updatable> updatables) {
		myUpdatables = updatables;
		updateLanguage(DEFAULT);
	}

	public Commandable createCommand(String command) {
		return createCommmand(command, "0");
	}
	
	public Commandable createCommmand(String command, String id) {
		String keyword = myLanguages.get(command);
		try {
			Class<?> clazz = Class.forName(myCommands.getString(keyword) + "Command");
			if (clazz.getSuperclass() == UpdatableCommand.class) {
				Constructor<?> ctor = clazz.getConstructor(Updatable.class);
				return (Commandable) ctor.newInstance(myUpdatables.get(id));
			} else {
				Constructor<?> ctor = clazz.getConstructor();
				return (Commandable) ctor.newInstance();
			}
		} catch (Exception e) {
			// I think that this should be NullCommand, and ExceptionCommand can be called
			// in Engine when iterating
			return new ExceptionCommand();
		}
	}
	
	public void updateLanguage(String lang) {
		String location = String.format("%s%s", LANGUAGE_BASE, lang);
		ResourceBundle rb = ResourceBundle.getBundle(location);
		for (String key : rb.keySet()) {
			String[] input = rb.getString(key).split("\\|");
			for (String s : input) {
				myLanguages.put(s, key);
			}
		}
		System.out.println(myLanguages);
	}
}
