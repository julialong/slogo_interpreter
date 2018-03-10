package commands.unbundler.multiple_unbundlers;

import java.util.List;

import commands.VariableReplacer;
import parser.Parser;
import view.Visualizer;


public class DoTimes extends MultipleUnbundler {
	
	private static final int NUM_ARGS = 2;

	private String variable;
	private double timesToRepeat;
	private List<String> unbundledArray;

	private static final int START_EXPRESSION = 2;

	private static final int START_INDEX = 0;
	private static final int STOP_INDEX = 1;
	
	public DoTimes(Visualizer vis, VariableReplacer var_replacer, Parser p) {
		super(vis, var_replacer, NUM_ARGS, p);
	}

	/**
	 * unbundles the given control command starting at index
	 * @param exp is the entire ArrayList of the input commands
	 * @return the String of the unbundled control command
	 */
	public String unbundle(List<String> exp) {
		int[] expressionIndex = findBrackets(exp, 0);
		int[] commandIndex = findBrackets(exp, 1);
		setNumbers(exp, expressionIndex[1]);
		unbundledArray = buildCommand(exp, variable, timesToRepeat, commandIndex[START_INDEX], commandIndex[STOP_INDEX]);
		modifyList(exp, commandIndex[STOP_INDEX]);
		return String.join(" ", unbundledArray);
	}

	/**
	 * Sets the given parameters based on the entries in the first set of brackets
	 * @param exp is is the entire ArrayList of the input commands
	 */
	private void setNumbers(List<String> exp, int stopIndex) {
		variable = exp.get(1);
		timesToRepeat = executeExpression(buildExpression(exp, START_EXPRESSION, stopIndex));
	}

	/**
	 *
	 * @param expression is the List of the expression to execute
	 * @return the value of the expression
	 */
	private double executeExpression(List<String> expression) {
		double answer = 0;
		if (expression.size() > 1){
			answer = getParser().parse(String.join(" ", expression));
		}
		else {
			answer = Double.parseDouble(expression.get(0));
		}
		return answer;
	}
}