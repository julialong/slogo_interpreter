package commands.unbundler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	public MakeUserInstruction(Visualizer vis, Parser p, Map<String, Function> dict) {
		super(vis, NUM_ARGS, p);
		dictionary = dict;
		visualizer = vis;
	}

	/**
	 * unbundles the given control command starting at index to crete a new variable
	 * @param exp is the entire ArrayList of the input commands
	 * @return the string variable name
	 */
	public String unbundle(List<String> exp) {
		commandName = exp.get(0);
		int[] variableIndex = findBrackets(exp, 0);
		int[] commandIndex = findBrackets(exp, 1);
		parameters = new ArrayList<>();
		commands = new ArrayList<>();
		buildExpression(exp, commandIndex[0], commandIndex[1]);
		addVariables(exp, variableIndex[0], variableIndex[1]);
		addFunction();
		modifyList(exp, commandIndex[1]);
		return Integer.toString(1);
	}

	private void addVariables(List<String> exp, int start, int end) {
		for (int i = start + 1; i < end; i++) {
			String current = exp.get(i);
			parameters.add(current);
		}
	}

	/**
	 * Builds the expression to be evaluated
	 * @param exp is the entire ArrayList of the input commands
	 * @return the index of the first left bracket
	 */
	private void buildExpression(List <String> exp, int start, int end) {
		for (int i = start + 1; i < end; i++) {
			String current = exp.get(i);
			commands.add(current);
		}
	}

	/**
	 * TODO: Unsure if this correctly modifies map
	 * @param variable string variable name
	 */
	private void addFunction() {
		func = new Function(visualizer, getParser(), commandName, parameters, commands);
		dictionary.put(commandName, func);
	}
	
	@Override
	protected double calcValue(List<String> args) {
		String unbundled = unbundle(argsToExp(args));
		visualizer.addNewFunc(func.toString());
		return getParser().parse(unbundled);
	}

}
