package parser.unbundler;

import java.util.ArrayList;
import java.util.List;

import parser.Parser;

public class IfUnbundler extends ControlUnbundler{

	private List<String> expression;
	private ArrayList<String> unbundledArray;
	private Parser parser;

	private boolean executeCommands;

	/**
	 * Creates an unbundler for the repeat command
	 */
	public IfUnbundler(Parser p) {
		parser = p;
	}

	/**
	 * unbundles the given control command starting at index
	 * @param exp is the entire ArrayList of the input commands
	 * @return the String of the unbundled control command
	 */
	public String unbundle(List<String> exp) {
		int[] commandIndex = findBrackets(exp, 0);
		expression = new ArrayList<>();
		System.out.println(exp);
		buildExpression(exp, commandIndex[0]);
		executeCommands = executeExpression(expression);
		buildCommand(exp, commandIndex[0], commandIndex[1]);
		modifyList(exp, commandIndex[1]);
		return String.join(" ", unbundledArray);
	}

	/**
	 * Builds the expression to be evaluated
	 * @param exp is the entire ArrayList of the input commands
	 * @return the index of the first left bracket
	 */
	private void buildExpression(List<String> exp, int end) {
		for (int i = 0; i < end; i++) {
			String current = exp.get(i);
			expression.add(current);
		}
	}

	private boolean executeExpression(List<String> expression) {
		boolean executeCommands;
		if (expression.size() <= 0) {
			executeCommands = false;
		} else {
			double answer = parser.parse(String.join(" ", expression));
			executeCommands = (answer != 0.0);
		}
		return executeCommands;
	}

	/**
	 * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
	 * @param exp is the entire ArrayList of the input commands
	 * @return the index where the command ends, or the last bracket
	 */
	private void buildCommand(List<String> exp, int start, int stop) {
		unbundledArray = new ArrayList<>();
		if (executeCommands) {
			for (int j = start + 1; j < stop; j++) {
				unbundledArray.add(exp.get(j));
			}
		}
		else {
			unbundledArray.add("#nocommands");
		}
	}
}
