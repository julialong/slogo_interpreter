package commands.unbundler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import commands.factory.VariableReplacer;
import commands.misc.Function;
import parser.Parser;
import view.Visualizer;

/**
 * Handles control command necessary to make a function
 * @author julialong, benhubsch
 */
public class MakeUserInstruction extends ControlUnbundler {

	private static final int NUM_ARGS = 3;

	private String commandName;
	private List<String> parameters;
	private List<String> commands;
	private Map<String, Function> dictionary;
	private Visualizer visualizer;
	private Function func;
	private VariableReplacer variable_replacer;

	private int[] variableIndex;
	private int[] commandIndex;

	/**
	 * Creates a new Make unbundler object
	 * @param visualizer is the current Visualizer being used in the session
	 * @param variableReplacer is the current variableReplacer object
	 * @param parser is the current Parser object
	 * @param dict is the current map of function names to function objects
	 */
	public MakeUserInstruction(Visualizer visualizer, VariableReplacer variableReplacer, Parser parser, Map<String, Function> dict) {
		super(visualizer, variableReplacer, NUM_ARGS, parser);
		dictionary = dict;
		this.visualizer = visualizer;
		variable_replacer = variableReplacer;
	}

	/**
	 * unbundles the given control command starting at index to crete a new variable
	 * @param exp is the entire ArrayList of the input commands
	 * @return the string variable name
	 */
	public String unbundle(List<String> exp) {
		commandName = exp.get(0);
		getIndex(exp);
		setUpLists();
		commands = buildExpression(exp, commandIndex[0] + 1, commandIndex[1]);
		addVariables(exp, variableIndex[0], variableIndex[1]);
		addFunction();
		modifyList(exp, commandIndex[1]);
		return Integer.toString(1);
	}

	/**
	 * Gets the current indexes of the variable and command brackets
	 * @param exp
	 */
	private void getIndex(List<String> exp) {
		variableIndex = findBrackets(exp, 0);
		commandIndex = findBrackets(exp, 1);
	}

	/**
	 * Sets up the parameter and command lists
	 */
	private void setUpLists() {
		parameters = new ArrayList<>();
		commands = new ArrayList<>();
	}

	/**
	 * Adds parameters to the parameter list
	 * @param exp is the list of te entire expression
	 * @param start is the index to start adding variables
	 * @param end is the index to stop adding variables
	 */
	private void addVariables(List<String> exp, int start, int end) {
		for (int i = start + 1; i < end; i++) {
			String current = exp.get(i);
			parameters.add(current);
		}
	}

	/**
	 * Adds the current function to the map for use later
	 */
	private void addFunction() {
		func = new Function(visualizer, variable_replacer, getParser(), commandName, parameters, commands);
		dictionary.put(commandName, func);
	}

	@Override
	protected double calcValue(List<String> args) {
		String unbundled = unbundle(argsToExp(args));
		visualizer.addNewFunc(func.toString());
		return getParser().parse(unbundled);
	}

}
