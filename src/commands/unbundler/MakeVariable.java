package commands.unbundler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parser.Parser;
import view.Visualizer;

public class MakeVariable extends ControlUnbundler{
	
	private static final int NUM_ARGS = 2;

	private List<String> expression;
	private Map<String, String> dictionary;
	private Visualizer myVis;
	
	public MakeVariable(Visualizer vis, Parser p, Map<String, String> dict) {
		super(vis, NUM_ARGS, p);
		dictionary = dict;
		myVis = vis;
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

