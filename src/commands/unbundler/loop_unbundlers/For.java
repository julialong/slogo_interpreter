package commands.unbundler.loop_unbundlers;

import java.util.List;

import commands.factory.VariableReplacer;
import commands.unbundler.ControlUnbundler;
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
	private double end;

	private static final int VARIABLE_INDEX = 1;
	private static final int END_INDEX = 3;

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
		end = Double.parseDouble(exp.get(END_INDEX));
	}

}
