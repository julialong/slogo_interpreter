package commands.unbundler;

import java.util.ArrayList;
import java.util.List;

import commands.factory.VariableReplacer;
import parser.Parser;
import view.Visualizer;

/**
 * Creates an object that handles the case where we need to perform an action a certain number of times,
 * with a certain start, end, and increment size
 * @author julialong
 */
public class For extends ControlUnbundler {

	private static final int NUM_ARGS = 2;

	/**
	 * Creates a For unbundler object
	 * @param visualizer is the current Visualizer being used in the session
	 * @param variableReplacer is the current variableReplacer object
	 * @param parser is the current Parser object
	 */
	public For(Visualizer visualizer, VariableReplacer variableReplacer, Parser parser) {
		super(visualizer, variableReplacer, NUM_ARGS, parser);
	}

	private String variable;
	private double start;
	private double end;
	private double increment;

	private static final int VARIABLE_INDEX = 1;
	private static final int START_INDEX = 2;
	private static final int END_INDEX = 3;
	private static final int INCREMENT_INDEX = 4;

	private ArrayList<String> unbundledArray;

	/**
	 * Unbundles For command to a longer string that the Parser can parse
	 * @param exp is the current expression
	 * @return the unbundled command as a string
	 */
	@Override
	protected String unbundle(List<String> exp) {
		setNumbers(exp);
		int[] commandIndex = findBrackets(exp, 1);
		buildCommand(exp, commandIndex[0], commandIndex[1]);
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

	/**
	 * Builds an unbundled command that repeats the correct number of times based on the execution value of the expression
	 * @param exp is the entire ArrayList of the input commands
	 * @return the index where the command ends, or the last bracket
	 */
	private void buildCommand(List<String> exp, int startIndex, int stopIndex) {
		unbundledArray = new ArrayList<>();
		for (double i = start; i < end; i+= increment) {
			for (int j = startIndex + 1; j < stopIndex; j++) {
				unbundledArray.add(replaceVariable(exp.get(j), i));
			}
		}
	}

	private String replaceVariable(String current, double currentIndex) {
		if (current.equals(variable)) {
			return Double.toString(currentIndex);
		} else {
			return current;
		}
	}

}
