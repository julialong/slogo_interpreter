package commands.unbundler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import commands.VariableReplacer;
import parser.Parser;
import view.Visualizer;

public class Repeat extends ControlUnbundler {

	private static final int NUM_ARGS = 2;

	private double toRepeat;
	private List<String> expression;
	private LinkedList<String> unbundledArray;


	public Repeat(Visualizer vis, VariableReplacer var_replacer, Parser p) {
		super(vis, var_replacer, NUM_ARGS, p);
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
		System.out.println("unbundled" + unbundledArray);
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
			toRepeat = 0;
		} else {
			toRepeat = getParser().parse(String.join(" ", expression));
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
		for (int i = 1; i <= (int) toRepeat; i++) {
			for (int j = start + 1; j < stop; j++) {
				unbundledArray.add(replaceVariable(":repcount", exp.get(j), i));
			}
		}
	}

	/**
	 * Replaces the variable with the value of the current index
	 *
	 * @param current      is the current string
	 * @param currentIndex is the value of the current position that needs to replace the variable
	 * @return
	 */
	private String replaceVariable(String variable, String current, double currentIndex) {
		if (current.equals(variable)) {
			return Double.toString(currentIndex);
		} else {
			return current;
		}
	}
}
