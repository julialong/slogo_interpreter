package commands.unbundler.conditional_unbundlers;

import java.util.List;

import commands.factory.VariableReplacer;
import parser.Parser;
import view.Visualizer;

/**
 * Handles If control command
 * @author julialong
 */

/**
 *
 */
public class If extends ConditionalUnbundler{
	
	private static final int NUM_ARGS = 2;
	private static final int START_INDEX = 0;
	private static final int STOP_INDEX = 1;

	private List <String> expression;
	private boolean executeCommands;
	private int[] commandIndex;

	/**
	 * Creates a new If unbundler class
	 * @param visualizer is the current Visualizer being used in the session
	 * @param variableReplacer is the current variableReplacer object
	 * @param parser is the current Parser object
	 */
	public If(Visualizer visualizer, VariableReplacer variableReplacer, Parser parser) {
		super(visualizer, variableReplacer, NUM_ARGS, parser);
	}

	/**
	 * unbundles the given control command starting at index
	 * @param exp is the entire ArrayList of the input commands
	 * @return the String of the unbundled control command
	 */
	public String unbundle(List<String> exp) {
		commandIndex = findBrackets(exp, 0);
		handleExpression(exp);
		List<String> unbundledArray = buildCommand(exp, commandIndex[START_INDEX], commandIndex[STOP_INDEX], executeCommands);
		modifyList(exp, commandIndex[1]);
		return String.join(" ", unbundledArray);
	}

	/**
	 * Builds and executes the expression
	 * @param exp is the list of the expression
	 */
	private void handleExpression(List<String> exp) {
		expression = buildExpression(exp, 0,  commandIndex[START_INDEX]);
		executeCommands = executeExpression(expression);
	}

}
