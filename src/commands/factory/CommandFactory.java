package commands.factory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import commands.Command;
import commands.misc.Null;
import parser.Function;
import parser.Parser;
import slogo_team07.Updatable;
import view.Visualizer;

public class CommandFactory {

	private static final String COMMANDS = "resources.commands.Command";
	private static final String FACTORIES = "resources.commands.Factory";
	private static final String LANGUAGE_BASE = "resources.languages.";
	private static final String DEFAULT = "English";

	private ResourceBundle myCommands = ResourceBundle.getBundle(COMMANDS);
	private ResourceBundle myFactories = ResourceBundle.getBundle(FACTORIES);
	private Map<String, Factory> myFactoryMap = createMap();
	private Map<String, String> myLanguages = new HashMap<>();
	private Set<String> myActives = new HashSet<>();
	private Map<String, Updatable> myUpdatables = new HashMap<>();
	private Map<String, Function> myFuncMap = new HashMap<>();
	private Map<String, String> myVarMap = new HashMap<>();
	private Visualizer myVis;
	private Parser myParser;
	
	public CommandFactory(Visualizer vis, Parser parser) {
		myVis = vis;
		myParser = parser;
		
		updateLanguage(DEFAULT);
	}

	public List<Command> createCommands(String command) {
		if (myFuncMap.containsKey(command)) {
			return Arrays.asList(myFuncMap.get(command));
		}
		
		String keyword = myLanguages.get(command);
		Factory factory = myFactoryMap.get(myFactories.getString(keyword));
		System.out.println(command);
		System.out.println(keyword);
		System.out.println(factory);
		try {
			return factory.create(keyword);
		} catch (CommandNotFoundException e) {
			return Arrays.asList(new Null(myVis));
		}
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

	public String getBundleValue(String keyword) {
		System.out.println("inside bundle value: " + keyword);
		return myCommands.getString(keyword);
	}

	public Set<String> getActives() {
		return myActives;
	}

	public Visualizer getVis() {
		return myVis;
	}

	public Parser getParser() {
		return myParser;
	}

	public Updatable getUpdatableById(String id) {
		return myUpdatables.get(id);
	}

	public Map<String, Updatable> getUpdatables() {
		return myUpdatables;
	}

	public Map<String, Function> getFuncMap() {
		return myFuncMap;
	}
	
	public Map<String, String> getVarMap() {
		return myVarMap;
	}
	
	private Map<String, Factory> createMap() {
		Map<String, Factory> factory_map = new HashMap<>();
		factory_map.put("Multiple", new MultipleFactory(this));
		factory_map.put("NonUpdatable", new NonUpdatableFactory(this));
		factory_map.put("Updatable", new UpdatableFactory(this));
		factory_map.put("Unbundler", new UnbundlerFactory(this));
		return factory_map;
	}

	public boolean isCommand(String string) {
		return myLanguages.containsKey(string);
	}

	public String getVar(String variable) {
		return myVarMap.get(variable);
	}

	public boolean isRegistered(String string) {
		return myVarMap.containsKey(string) 
				|| myLanguages.containsKey(string)
				|| myFuncMap.containsKey(string);
	}
}
