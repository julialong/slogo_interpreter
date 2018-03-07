package commands;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import commands.misc.Null;
import parser.Parser;
import slogo_team07.Updatable;
import view.Visualizer;
import commands.unbundler.ControlUnbundler;
import commands.multiples.Multiple;

public class CommandFactory {

	private static final String COMMANDS = "resources.commands.Command";
	private static final String LANGUAGE_BASE = "resources.languages.";
	private static final String DEFAULT = "English";

	private ResourceBundle myCommands = ResourceBundle.getBundle(COMMANDS);
	private Map<String, String> myLanguages = new HashMap<>();
	private Set<String> myActives = new HashSet<>();
	private Map<String, Updatable> myUpdatables = new HashMap<>();
	private Visualizer myVis;
	private Parser myParser;

	public CommandFactory(Visualizer vis, Parser parser) {
		myVis = vis;
		myParser = parser;
		updateLanguage(DEFAULT);
	}

	public List<Command> createCommands(String command) {
		String keyword = myLanguages.get(command);
		List<Command> commandables = new ArrayList<>();
		try {
			Class<?> clazz = Class.forName(myCommands.getString(keyword));
			Constructor<?> ctor;
			if (clazz.getSuperclass().equals(UpdatableCommand.class)) {
				ctor = clazz.getDeclaredConstructor(new Class[] { Visualizer.class, Updatable.class });
				for (Updatable active : getActiveUpdatables()) {
					commandables.add((Command) ctor.newInstance(myVis, active));
				}
			} else if (clazz.getSuperclass().equals(ControlUnbundler.class)) {
				// CURRENTLY DOESN'T HANDLE TO AND MAKE/SET, BECAUSE THOSE CONSTRUCTORS ARE DIFFERENT
				// THEY SHOULD ALMOST CERTAINLY BE SUBCLASSED
				ctor = clazz.getDeclaredConstructor(new Class[] { Visualizer.class, Parser.class });
				commandables.add((Command) ctor.newInstance(myVis, myParser));
			} else if (clazz.getSuperclass().equals(Multiple.class)) {
				ctor = clazz.getDeclaredConstructor(new Class[] { Visualizer.class, Parser.class, Set.class, Map.class });
				commandables.add((Command) ctor.newInstance(myVis, myParser, myActives, myUpdatables));
			}
			else {
				ctor = clazz.getDeclaredConstructor(Visualizer.class);
				commandables.add((Command) ctor.newInstance(myVis));
			}
		} catch (Exception e) {
			e.printStackTrace();
			commandables.add(new Null(myVis));
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

	private List<Updatable> getActiveUpdatables() {
		return myUpdatables.keySet().stream()
				.filter(myActives::contains)
				.map(myUpdatables::get)
				.collect(Collectors.toList());

	}
}
