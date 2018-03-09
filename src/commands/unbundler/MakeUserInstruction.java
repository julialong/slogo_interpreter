package commands.unbundler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import commands.VariableReplacer;
import parser.Function;
import parser.Parser;
import view.Visualizer;

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

	public MakeUserInstruction(Visualizer vis, VariableReplacer var_replacer, Parser parser, Map<String, Function> dict) {
		super(vis, var_replacer, NUM_ARGS, parser);
		dictionary = dict;
		visualizer = vis;
		variable_replacer = var_replacer;
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
		commands = buildExpression(exp, commandIndex[0], commandIndex[1]);
		addVariables(exp, variableIndex[0], variableIndex[1]);
		addFunction();
		modifyList(exp, commandIndex[1]);
		return Integer.toString(1);
	}

	private void getIndex(List<String> exp) {
		variableIndex = findBrackets(exp, 0);
		commandIndex = findBrackets(exp, 1);
	}

	private void setUpLists() {
		parameters = new ArrayList<>();
		commands = new ArrayList<>();
	}

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
