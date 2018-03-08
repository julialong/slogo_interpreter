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
	
	public MakeVariable(Visualizer vis, Parser p, Map<String, String> dict) {
		super(vis, NUM_ARGS, p);
		dictionary = dict;
	}

	/**
	 * unbundles the given control command starting at index to crete a new variable
	 * @param exp is the entire ArrayList of the input commands
	 * @return the string variable name
	 */
	public String unbundle(List<String> exp) {
		System.out.println("exp: " + exp);
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
}

