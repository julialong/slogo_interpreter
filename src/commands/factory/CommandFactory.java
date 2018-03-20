package commands.factory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import commands.Command;
import commands.misc.Function;
import parser.Parser;
import slogo_team07.Updatable;
import view.Visualizer;

/**
 * A factory for creating Command objects.
 *
 * @author benhubsch
 * 
 * The CommandFactory is our main factory class. It distributes input appropriately
 * so that the proper Commands can be instantiated by the right factory. It also is the
 * main state holder for everything that any of the potentially instantiated commands
 * might need, including the language conversion and many of its attributes.
 */
public class CommandFactory implements VariableReplacer {

	public static final String ID = "id";
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
	
	/**
	 * Instantiates a new CommandFactory object.
	 *
	 * @param vis the vis
	 * @param parser the parser
	 */
	public CommandFactory(Visualizer vis) {
		myVis = vis;

		updateLanguage(DEFAULT);
	}

	/**
	 * Creates a new list of Command objects by delegating to the appropriate instance of
	 * a Factory.
	 *
	 * @param command The string command that a user entered.
	 * @return The List<Command> that is returned for the Parser to iterate over.
	 */
	public Iterable<Command> createCommands(String command) {
		List<Command> result;
		if (myFuncMap.containsKey(command)) {
			result = Arrays.asList(myFuncMap.get(command));
		} else {
			String keyword = myLanguages.get(command);
			Factory factory = myFactoryMap.get(myFactories.getString(keyword));
			result = factory.create(keyword);
		}
		return () -> result.iterator();
	}

	/**
	 * Updates the current language after the user chooses a new language in the UI. It
	 * reverses the map of commands from the resource file to make it more searchable
	 * for later inputs.
	 *
	 * @param lang the lang
	 */
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

	/**
	 * Gets the String object associated with a specific keyword from the Command
	 * bundle. It is used for reflection in some of the other Factory methods.
	 *
	 * @param keyword the keyword
	 * @return String
	 */
	public String getBundleValue(String keyword) {
		return myCommands.getString(keyword);
	}

	/**
	 * Gets the Set<String> object.
	 *
	 * @return Set<String>
	 */
	public Set<String> getActives() {
		return myActives;
	}

	/**
	 * Gets the Visualizer object.
	 *
	 * @return Visualizer
	 */
	public Visualizer getVis() {
		return myVis;
	}

	/**
	 * Gets the Parser object.
	 *
	 * @return Parser
	 */
	public Parser getParser() {
		return myParser;
	}

	/**
	 * Gets the Updatable object.
	 *
	 * @param id the id
	 * @return Updatable
	 */
	public Updatable getUpdatableById(String id) {
		return myUpdatables.get(id);
	}

	/**
	 * Gets the Map<String, Updatable> object.
	 *
	 * @return Map<String, Updatable>
	 */
	public Map<String, Updatable> getUpdatables() {
		return myUpdatables;
	}

	/**
	 * Gets the Map<String, Function> object.
	 *
	 * @return Map<String,Function>
	 */
	public Map<String, Function> getFuncMap() {
		return myFuncMap;
	}

	/**
	 * Gets the Map<String, String> object.
	 *
	 * @return Map<String, String>
	 */
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

	/**
	 * Checks if the given string exists in the realm of known commands.
	 *
	 * @param string the string
	 * @return true, if is command
	 */
	public boolean isCommand(String string) {
		return myLanguages.containsKey(string);
	}

	/**
	 * Gets the variable value associated with a given variable parameter.
	 * For example, a given variable value ":x" might return the String value
	 * "10."
	 *
	 * @param variable the variable
	 * @return String
	 */
	public String getVar(String variable) {
		return myVarMap.get(variable);
	}

	/**
	 * Checks the given string is registered anywhere within our system or if it's
	 * a piece of input that isn't recognized.
	 *
	 * @param string the string
	 * @return true, if is registered
	 */
	public boolean isRegistered(String string) {
		return myVarMap.containsKey(string) 
				|| myLanguages.containsKey(string)
				|| myFuncMap.containsKey(string);
	}

	/* (non-Javadoc)
	 * @see commands.factory.VariableReplacer#replace(java.lang.String)
	 */
	@Override
	public String replace(String var) {
		return myVarMap.containsKey(var) ? myVarMap.get(var) : "0.0";
	}

	/**
	 * Gets any Updatable object, which is used on "cold" calls to id that
	 * aren't associated with an UpdatableCommand. It will return one of the
	 * Updatables that can be used.
	 *
	 * @return Updatable
	 */
	public Updatable getCurrent() {
		return myUpdatables.entrySet().iterator().next().getValue();
	}
	
	public void setParser(Parser parser) {
		myParser = parser;
	}
}
