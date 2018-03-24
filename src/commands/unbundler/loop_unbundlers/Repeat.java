package commands.unbundler.loop_unbundlers;

import java.util.List;

import commands.factory.VariableReplacer;
import parser.Parser;
import view.Visualizer;

public class Repeat extends LoopUnbundler {

	private static final int NUM_ARGS = 2;

	private double timesToRepeat;
	private List<String> expression;
	private List<String> unbundledArray;


	/**
	 * Creates a new Repeat unbundler class
	 * @param visualizer is the current Visualizer class
	 * @param variableReplacer is the current variableReplacer class
	 * @param parser is the current parser
	 */
	public Repeat(Visualizer visualizer, VariableReplacer variableReplacer, Parser parser) {
		super(visualizer, variableReplacer, NUM_ARGS, parser);
	}

	/**
	 * unbundles the given control command starting at index
	 *
	 * @param exp is the entire ArrayList of the input commands
	 * @return the String of the unbundled control command
	 */
	public String unbundle(List<String> exp) {
		int[] commandIndex = findBrackets(exp, 0);
		expression = buildExpression(exp, 0, commandIndex[0]);
		timesToRepeat = executeExpression(expression);
		unbundledArray = buildCommand(exp, ":repcount", timesToRepeat, commandIndex[0], commandIndex[1]);
		modifyList(exp, commandIndex[1]);
		return String.join(" ", unbundledArray);
	}
}
