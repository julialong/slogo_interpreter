package commands.unbundler;

import java.util.ArrayList;
import java.util.List;

import parser.Parser;
import view.Visualizer;

public class For extends MultipleUnbundler {

	private static final int NUM_ARGS = 2;

	public For(Visualizer vis, Parser p) {
		super(vis, NUM_ARGS, p);
	}

	private String variable;
	private double start;
	private double end;
	private double increment;

	private static final int VARIABLE_INDEX = 1;
	private static final int START_INDEX = 2;
	private static final int END_INDEX = 3;
	private static final int INCREMENT_INDEX = 4;

	private List<String> unbundledArray;

	/**
	 * Unbundles For command to a longer string that the Parser can parse
	 * @param exp is the current expression
	 * @return the unbundled command as a string
	 */
	@Override
	protected String unbundle(List<String> exp) {
		setNumbers(exp);
		int[] commandIndex = findBrackets(exp, 1);
		unbundledArray = buildCommand(exp, variable, end, commandIndex[0], commandIndex[1]);
		modifyList(exp, commandIndex[1]);
		return String.join(" ", unbundledArray);
	}

	/**
	 * Sets the given parameters based on the entries in the first set of brackets
	 * @param exp is is the entire ArrayList of the input commands
	 */
	private void setNumbers(List<String> exp) {
		variable = exp.get(VARIABLE_INDEX);
		start = Double.parseDouble(exp.get(START_INDEX));
		end = Double.parseDouble(exp.get(END_INDEX));
		increment = Double.parseDouble(exp.get(INCREMENT_INDEX));
	}

}
