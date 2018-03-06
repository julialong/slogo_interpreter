package commands;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import commands.misc.NullCommand;
import slogo_team07.Updatable;
import view.Visualizer;

public class CommandFactory {

	private static final String COMMANDS = "resources.commands.Command";
	private static final String LANGUAGE_BASE = "resources.languages.";
	private static final String DEFAULT = "English";

	ResourceBundle myCommands = ResourceBundle.getBundle(COMMANDS);
	Map<String, String> myLanguages = new HashMap<>();
	Visualizer myVis;

	public CommandFactory(Visualizer vis) {
		myVis = vis;
		updateLanguage(DEFAULT);
	}
	
	public List<Commandable> createCommands(String command, List<Updatable> actives) {
		String keyword = myLanguages.get(command);
		List<Commandable> commandables = new ArrayList<>();
		try {
			Class<?> clazz = Class.forName(myCommands.getString(keyword) + "Command");
			if (clazz.getSuperclass() == UpdatableCommand.class) {
				Constructor<?> ctor = clazz.getDeclaredConstructor(new Class[] {Visualizer.class, Updatable.class});
				for (Updatable active : actives) {
					commandables.add((Commandable) ctor.newInstance(myVis, active));
				}
			} else {
				Constructor<?> ctor = clazz.getDeclaredConstructor(Visualizer.class);
				commandables.add((Commandable) ctor.newInstance(myVis));
			}
		} catch (Exception e) {
			commandables.add(new NullCommand(myVis));
		}
		
		return commandables;
	}

	public void updateLanguage(String lang) {
		String location = String.format("%s%s", LANGUAGE_BASE, lang);
		ResourceBundle rb = ResourceBundle.getBundle(location);
		for (String key : rb.keySet()) {
			String[] input = rb.getString(key).split("\\|");
			for (String s : input) {
				myLanguages.put(s.replace("\\", ""), key);
			}
		}
	}
}
