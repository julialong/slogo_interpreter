package commands.unbundler.multiple_unbundlers;

import java.util.List;

import commands.VariableReplacer;
import parser.Parser;
import view.Visualizer;

public class Repeat extends MultipleUnbundler {

	private static final int NUM_ARGS = 2;

	private double timesToRepeat;
	private List<String> expression;
	private List<String> unbundledArray;



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
		expression = buildExpression(exp, 0, commandIndex[0]);
		executeExpression();
		unbundledArray = buildCommand(exp, ":repcount", timesToRepeat, commandIndex[0], commandIndex[1]);
		modifyList(exp, commandIndex[1]);
		return String.join(" ", unbundledArray);
	}

	private void executeExpression() {
		if (expression.size() <= 0) {
			timesToRepeat = 0;
		} else {
			timesToRepeat = getParser().parse(String.join(" ", expression));
		}
	}
}
