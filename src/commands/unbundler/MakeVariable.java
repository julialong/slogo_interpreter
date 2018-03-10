package commands.unbundler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import commands.CommandArgsUnfilledException;
import commands.Result;
import commands.factory.VariableReplacer;
import parser.Parser;
import view.Visualizer;

public class MakeVariable extends ControlUnbundler {
	
	private static final int NUM_ARGS = 2;

	private List<String> expression;
	private Map<String, String> dictionary;
	private Visualizer myVis;
	
	public MakeVariable(Visualizer vis, VariableReplacer var_replacer, Parser p, Map<String, String> dict) {
		super(vis, var_replacer, NUM_ARGS, p);
		dictionary = dict;
		myVis = vis;
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
		expression = new ArrayList<>();
		buildExpression(exp, 1, exp.size());
		addVariable(variableName);
		modifyList(exp, 1);
		return String.join(" ", expression);
	}

	/**
	 * Builds the expression to be evaluated
	 * @param exp is the entire ArrayList of the input commands
	 * @return the index of the first left bracket
	 */
	private void buildExpression(List<String> exp, int start, int end) {
		for (int i = start; i < end; i++) {
			String current = exp.get(i);
			expression.add(current);
		}
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

