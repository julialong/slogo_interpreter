package commands.unbundler;

import java.util.List;
import java.util.Map;

import commands.CommandArgsUnfilledException;
import commands.Result;
import commands.factory.VariableReplacer;
import parser.Parser;
import view.Visualizer;

/**
 * Handles control command necessary to define a variable
 * @author julialong, benhubsch
 */
public class MakeVariable extends ControlUnbundler {
	
	private static final int NUM_ARGS = 2;

	private List<String> expression;
	private Map<String, String> dictionary;
	private Visualizer myVis;

	/**
	 * Creates a MakeVariable unbundler object
	 * @param visualizer is the current Visualizer being used in the session
	 * @param variableReplacer is the current variableReplacer object
	 * @param parser is the current Parser object
	 * @param dict is the current map of function names to function objects
	 */
	public MakeVariable(Visualizer visualizer, VariableReplacer variableReplacer, Parser parser, Map<String, String> dict) {
		super(visualizer, variableReplacer, NUM_ARGS, parser);
		dictionary = dict;
		myVis = visualizer;
	}
	
	@Override
	public String execute() {
		if (! isReady()) {
			throw new CommandArgsUnfilledException("This Command object needs more arguments to finish executing.");
		}

		double ans = calcValue(getArgs());
		visCommand(new Result(ans));
		return Double.toString(ans);
	}

	/**
	 * unbundles the given control command starting at index to crete a new variable
	 * @param exp is the entire ArrayList of the input commands
	 * @return the string variable name
	 */
	public String unbundle(List<String> exp) {
		String variableName = exp.get(0);
		expression = buildExpression(exp, 1, exp.size());
		addVariable(variableName);
		modifyList(exp, 1);
		return String.join(" ", expression);
	}

	/**
	 * @param variable string variable name
	 */
	private void addVariable(String variable) {
		dictionary.put(variable, String.join(" ", expression));
	}
	
	@Override
	protected double calcValue(List<String> args) {
		List<String> input = argsToExp(args);
		String variable = input.get(0);
		String unbundled = unbundle(input);
		double value = getParser().parse(unbundled);
		myVis.addNewVar(variable, Double.toString(value));
		return value;
	}
}

