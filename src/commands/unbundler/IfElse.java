package commands.unbundler;

import java.util.List;

import commands.VariableReplacer;
import parser.Parser;
import view.Visualizer;

/**
 * Handles IfElse control command
 * @author julialong
 */
public class IfElse extends ConditionalUnbundler {
	
	private static final int NUM_ARGS = 3;

    /**
     * Creates new IfElse unbundler object
     * @param vis is the current Visualizer
     * @param parser is the current Parser
     */
    public IfElse(Visualizer vis, VariableReplacer var_replacer, Parser parser) {
		super(vis, var_replacer, NUM_ARGS, parser);
	}

    /**
     * unbundles the given control command starting at index
     * @param exp is the entire ArrayList of the input commands
     * @return the String of the unbundled control command
     */
    public String unbundle(List<String> exp) {
        int[] trueCommandIndex = findBrackets(exp, 0);
        int[] falseCommandIndex = findBrackets(exp, 1);
        List<String> expression = buildExpression(exp, trueCommandIndex[0]);
        boolean executeCommands = executeExpression(expression);
        List<String> unbundledArray = buildCommand(exp, trueCommandIndex[0], trueCommandIndex[1], falseCommandIndex[0], falseCommandIndex[1], executeCommands);
        modifyList(exp, falseCommandIndex[1]);
        return String.join(" ", unbundledArray);
    }
}
