package commands.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
 *         The CommandFactory is our main factory class. It distributes input
 *         appropriately so that the proper Commands can be instantiated by the
 *         right factory. It also is the main state holder for everything that
 *         any of the potentially instantiated commands might need, including
 *         the language conversion and many of its attributes.
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
	private List<String> myActives = new ArrayList<>();
	private Map<String, Updatable> myUpdatables = new HashMap<>();
	private Map<String, Function> myFuncMap = new HashMap<>();
	private Map<String, String> myVarMap = new HashMap<>();
	private Visualizer myVis;
	private Parser myParser;

	/**
	 * Instantiates a new CommandFactory object.
	 *
	 * @param vis the vis
	 */
	public CommandFactory(Visualizer vis) {
		myVis = vis;

		updateLanguage(DEFAULT);
	}

	/**
	 * Creates a new list of Command objects by delegating to the appropriate
	 * instance of a Factory.
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
	 * Updates the current language after the user chooses a new language in the UI.
	 * It reverses the map of commands from the resource file to make it more
	 * searchable for later inputs.
	 *
	 * @param lang the lang
	 */
	public void updateLanguage(String lang) {
		myLanguages.clear();
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
	 * Checks the given string is registered anywhere within our system or if it's a
	 * piece of input that isn't recognized.
	 *
	 * @param string the string
	 * @return true, if is registered
	 */
	public boolean isKnownCommand(String string) {
		return myVarMap.containsKey(string) || myLanguages.containsKey(string) || myFuncMap.containsKey(string);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see commands.factory.VariableReplacer#replace(java.lang.String)
	 */
	@Override
	public String replace(String var) {
		if (!isVariable(var)) {
			return var;
		}
		return myVarMap.containsKey(var) ? myVarMap.get(var) : "0.0";
	}

	/**
	 * Sets the parser.
	 *
	 * @param parser the new parser
	 */
	public void setParser(Parser parser) {
		myParser = parser;
	}

	/**
	 * Gets the String object representing the id of the current Updatable. If there
	 * isn't one, it just gets the id of any active turtle.
	 *
	 * @param current the current
	 * @return String
	 */
	public String getId(Updatable current) {
		if (current == null) {
			System.out.println("inside");
			current = getAny();
		}
		return Double.toString(current.getId());
	}

	private Updatable getAny() {
		String curr = myActives.iterator().next();
		return myUpdatables.get(curr);
	}

	/**
	 * Gets the String object associated with a specific keyword from the Command
	 * bundle. It is used for reflection in some of the other Factory methods.
	 *
	 * @param keyword the keyword
	 * @return String
	 */
	protected String getBundleValue(String keyword) {
		return myCommands.getString(keyword);
	}

	/**
	 * Gets the Set<String> object.
	 *
	 * @return Set<String>
	 */
	protected List<String> getActives() {
		return myActives;
	}

	/**
	 * Gets the Visualizer object.
	 *
	 * @return Visualizer
	 */
	protected Visualizer getVis() {
		return myVis;
	}

	/**
	 * Gets the Parser object.
	 *
	 * @return Parser
	 */
	protected Parser getParser() {
		return myParser;
	}

	/**
	 * Gets the Updatable object.
	 *
	 * @param id the id
	 * @return Updatable
	 */
	protected Updatable getUpdatableById(String id) {
		return myUpdatables.get(id);
	}

	/**
	 * Gets the Map<String, Updatable> object.
	 *
	 * @return Map<String, Updatable>
	 */
	protected Map<String, Updatable> getUpdatables() {
		return myUpdatables;
	}

	/**
	 * Gets the Map<String, Function> object.
	 *
	 * @return Map<String,Function>
	 */
	protected Map<String, Function> getFuncMap() {
		return myFuncMap;
	}

	/**
	 * Gets the Map<String, String> object.
	 *
	 * @return Map<String, String>
	 */
	protected Map<String, String> getVarMap() {
		return myVarMap;
	}

	/**
	 * Checks if the given string exists in the realm of known commands.
	 *
	 * @param string the string
	 * @return true, if is command
	 */
	protected boolean isCommand(String string) {
		return myLanguages.containsKey(string);
	}

	/**
	 * Gets the variable value associated with a given variable parameter. For
	 * example, a given variable value ":x" might return the String value "10."
	 *
	 * @param variable the variable
	 * @return String
	 */
	protected String getVar(String variable) {
		return myVarMap.get(variable);
	}

	private Map<String, Factory> createMap() {
		Map<String, Factory> factory_map = new HashMap<>();
		factory_map.put("Multiple", new MultipleFactory(this));
		factory_map.put("NonUpdatable", new NonUpdatableFactory(this));
		factory_map.put("Updatable", new UpdatableFactory(this));
		factory_map.put("Unbundler", new UnbundlerFactory(this));
		return factory_map;
	}
	

	private boolean isVariable(String string) {
		return string.matches(Parser.VARIABLE_REGEX);
	}

}
