package parser.unbundler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import parser.Parser;

public class RepeatUnbundler extends ControlUnbundler {

	private double repeat;

	private List<String> expression;
	private LinkedList<String> unbundledArray;
	private Parser parser;

	/**
	 * Creates an unbundler for the repeat command
	 */
	public RepeatUnbundler(Parser p) {
		parser = p;
	}

	/**
	 * unbundles the given control command starting at index
	 *
	 * @param exp is the entire ArrayList of the input commands
	 * @return the String of the unbundled control command
	 */
	public String unbundle(List<String> exp) {
		int[] commandIndex = findBrackets(exp, 0);
		expression = new ArrayList<>();
		buildExpression(exp, commandIndex[0]);
		executeExpression();
		buildCommand(exp, commandIndex[0], commandIndex[1]);
		modifyList(exp, commandIndex[1]);
		return String.join(" ", unbundledArray);
	}

	/**
	 * Builds the expression to be evaluated
	 *
	 * @param exp is the entire ArrayList of the input commands
	 * @return the index of the first left bracket
	 */
	private void buildExpression(List<String> exp, int end) {
		for (int i = 0; i < end; i++) {
			String current = exp.get(i);
			expression.add(current);
		}
	}

	private void executeExpression() {
		if (expression.size() <= 0) {
			repeat = 0;
		} else {
			repeat = parser.parse(String.join(" ", expression));
		}
	}

	/**
	 * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
	 *
	 * @param exp is the entire ArrayList of the input commands
	 * @return the index where the command ends, or the last bracket
	 */
	private void buildCommand(List<String> exp, int start, int stop) {
		unbundledArray = new LinkedList<>();
		for (int i = 0; i < (int) repeat; i++) {
			for (int j = start + 1; j < stop; j++) {
				unbundledArray.add(exp.get(j));
			}
		}
	}
}